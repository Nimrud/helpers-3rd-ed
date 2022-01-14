package com.epam.prd.jjop.helpers.unmodifiableList_vs_List_of;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The purpose of this class is to show the difference between:
 * => .unmodifiableList()
 * => List.of()
 *
 * @author Krzysztof Jaczewski
 */

public class Main {

    private static List<Integer> list, list_second;

    public static void main(String[] args) {

        /**
         * SUMMARY of the difference between .unmodifiableList() vs List.of():
         * while we cannot perform any operations on those 'types' of lists,
         * contents of List.of() never change, while the values of .unmodifiableList() can change
         */

        /**
         * DETAILS:
         *
         * Step 1: we create a simple array and print the contents into the console:
         */
        list = Arrays.asList(1, 2);
        System.out.println("list (original) = " + list);

        /**
         * ===> unmodifiableList <===
         * syntax: Collections.unmodifiableList(original_list);
         *
         * Step 2: we run a static method that uses Collections.unmodifiableList method (at the very bottom of this class).
         * We can use that method if we:
         * - want to have a list that no one can perform any operations upon;
         * - don't want to expose the original list to any other classes - that way we do not use the original list,
         * but a copy (original list is encapsulated).
         * This should display the exact same array to the console.
         */
        System.out.println("list (unmodifiableList) = " + getList(list));

        /**
         * Step 3: We modify the original array: set first element to 4 and second to 25
         * (uncomment to run)
         */
//        list.set(0, 4);
//        list.set(1, 25);
//        System.out.println("list (original) = " + list);

        /**
         * Step 4: we display the unmodifiableList (uncomment below)
         * WHOA! The list has changed, despite it was supposed to be 'unmodifiable'!
         * Bug? Were we deceived?
         * Not at all. We can't make any operations on this copy (like add, change or remove elements),
         * but the original array CAN change.
         * This means that if the original list changes, those changes will be reflected by the unmodifiableList() method.
         *
         * So basically, if we want some class NOT to have power to change a list, but still have access to its contents,
         * we can use Collections.unmodifiableList() to just read the values of it. Operating on a copy of a list
         * does prevent from making any harm to the original list.
         */
//        System.out.println("list (unmodifiableList) = " + showUnmodifiedList(list));

        /**
         * CONCLUSION of unmodifiableList:
         * -> creates a copy of a list that can be safely passed to other classes
         * -> other classes cannot change (and don't have access to) the original list, assuming it's 'private'
         * -> can't make any operation on unmodifiableList (e.g. add anything) but the list itself CAN change (example at:
         * @see com.epam.prd.jjop.helpers.unmodifiableList_vs_List_of.example.SomeOtherClass
         */


        /**
         * ===> List.of <===
         *
         * List.of() creates a list that cannot change.
         * Neither can you change anything inside after it's been created, nor add/remove any elements.
         * Trying to do so will cause UnsupportedOperationException (uncomment to run & see)
         */
        list_second = List.of(78, 211);
//        list_second.set(0, 31);
//        list_second.set(1, 28);
//        list_second.add(13);
//        list_second.remove(1);
        System.out.println("list_second = " + list_second);

    }

    /**
     * Method should be package-private or public, due to it's purpose (delivering a copy of a private list to other classes)
     *
     * @param list any restricted-access list inside this class
     * @return a copy of this restricted-access list
     */
    public static List<Integer> getList(List<Integer> list) {
        return Collections.unmodifiableList(list);
    }
}
