package ru.nickb.ktlnvksdk.common.manager

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentTransaction
import ru.nickb.ktlnvksdk.ui.activity.BaseActivity
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment
import java.util.*

class MyFragmentManager {

    private val EMPTY_FRAGMENT_STACK_SIZE = 1

    private var mFragmentStack = Stack<BaseFragment>()

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
        val canRemove = fragment != null && mFragmentStack.size > EMPTY_FRAGMENT_STACK_SIZE

        if (canRemove) {
            val transaction = activity.supportFragmentManager.beginTransaction()

            mFragmentStack.pop()
            mCurrentFragment = mFragmentStack.lastElement()

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
                mFragmentStack = Stack()
            }

            mFragmentStack.add(fragment)

            commitTransaction(activity, transaction)

        }
    }

    private fun commitTransaction(activity: BaseActivity, transaction: FragmentTransaction) {
        transaction.commit()

        activity.fragmentOnScreen(mCurrentFragment!!)
    }

    private fun isAlreadyContains(fragment: BaseFragment?): Boolean {
        return if (fragment == null) {
            false
        } else mCurrentFragment != null && mCurrentFragment!!.javaClass.name == fragment.javaClass.name

    }


    fun getFragmentStack(): Stack<BaseFragment> {
        return mFragmentStack
    }

}