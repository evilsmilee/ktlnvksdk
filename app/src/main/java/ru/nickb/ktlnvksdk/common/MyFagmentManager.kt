package ru.nickb.ktlnvksdk.common

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentTransaction
import ru.nickb.ktlnvksdk.ui.activity.BaseActivity
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment
import java.util.*


open class  MyFragmentManager {


    var fragmentStack: Stack<BaseFragment> = Stack()


    private var mCurrentFragment: BaseFragment? = null



 fun setFragment(activity: BaseActivity?, fragment: BaseFragment, @IdRes containerId: Int) {
     if (activity != null && !activity.isFinishing && !isAlreadyContains(fragment)) {
            val fragmentTransaction = createAddTransaction(activity, fragment, false)
            fragmentTransaction.replace(containerId, fragment)
            commitAddTransaction(activity, fragment, fragmentTransaction, false)
        }
    }

    fun addFragment(activity: BaseActivity?, fragment: BaseFragment, @IdRes containerId: Int) {
        if (activity != null && !activity.isFinishing && !isAlreadyContains(fragment)) {
            val fragmentTransaction = createAddTransaction(activity, fragment, true)
            fragmentTransaction.add(containerId, fragment)
            commitAddTransaction(activity, fragment, fragmentTransaction, true)
        }
    }


    fun removeCurrentFragment(activity: BaseActivity): Boolean {
        return removeFragment(activity, mCurrentFragment)
    }

    fun removeFragment(activity: BaseActivity, fragment: BaseFragment?): Boolean {
        val canRemove = fragment != null && fragmentStack.size > Companion.EMPTY_FRAGMENT_STACK_SIZE

        if (canRemove) {
            val transaction = activity.supportFragmentManager.beginTransaction()

            fragmentStack.pop()
            mCurrentFragment = fragmentStack.lastElement()

            transaction.remove(fragment!!)
            commitTransaction(activity, transaction)
        }
        return canRemove
    }


    private fun createAddTransaction(
        activity: BaseActivity, fragment: BaseFragment,
        addToBackStack: Boolean
    ): FragmentTransaction {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.tag)
        }

        return fragmentTransaction
    }


    private fun commitAddTransaction(
        activity: BaseActivity, fragment: BaseFragment, transaction: FragmentTransaction?,
        addToBackStack: Boolean
    ) {
        if (transaction != null) {
            mCurrentFragment = fragment

            if (!addToBackStack) {
                fragmentStack = Stack()
            }

            fragmentStack.add(fragment)

            commitTransaction(activity, transaction)

        }
    }

    private fun commitTransaction(activity: BaseActivity, transaction: FragmentTransaction) {
        transaction.commit()

        mCurrentFragment?.let { activity.fragmentOnScreen(it) }
    }

    private fun isAlreadyContains(fragment: BaseFragment?): Boolean {
        return if (fragment == null) {
            false
        } else mCurrentFragment?.javaClass?.name == fragment.javaClass.name

    }

    companion object {
        const val  EMPTY_FRAGMENT_STACK_SIZE: Int = 1
    }


}