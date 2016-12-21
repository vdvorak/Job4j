package ru.matevosyan.start;

import ru.matevosyan.models.Item;

import java.util.Random;

/**
 * Class Tracker created for saving all items that have been created.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Tracker {

    /**
     * final variable int ITEMS_CAP is items array capacity.
     */

    private static final int ITEMS_CAP = 3;

    /**
     * Instance variable items which is array types is hold all created items.
     */

    private  Item[] items = new Item[ITEMS_CAP];

    /**
     * int variable position is hold position elements in array items.
     */

    private int position = 0;

    /**
     *Instance variable RM is created for value for items id.
     */

    private Random rm = new Random();

    /**
     * Method add created for add new item to array items.
     * it's may the specific type of item, for example: new Task or new Bug.
     * @param item which is the new item.
     * @return item that added to the items array
     */

    public Item add(Item item) {

        item.setId(this.generateId());
        this.items[position] = item;
        position++;
        return item;
    }

    /**
     * Method deleteItem created for deleted exist item from array of items.
     * it's may remove the specific type of item: Task or Bug.
     * @param id it is for determine which of this specific item must be deleted.
     * @return itemsDelete array without deleted item
     */

    public Item[] deleteItem(String id) {

        Item[] itemsDelete = new Item[items.length - 1];

        for (int i = 0; i < items.length; i++) {

            if (items[i] != null && items[i].getId().equals(id)) {

                for (int j = 0; j < items.length - 1; j++) {
                    items[j] = items[j + 1];
                }

            }

        }

        for (int i = 0; i < items.length - 1; i++) {
            itemsDelete[i] = items[i];
        }

       return itemsDelete;
    }

    /**
     * Method editItem created for edit exist item from array of items.
     * it's may edit the specific type of item: Task or Bug.
     * @param fresh it is specific item that you want to edit.
     */

    public void editItem(Item fresh) {
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            if (item != null && item.getId().equals(fresh.getId())) {
                items[index] = fresh;
                break;
            }
        }
    }

    /**
     * Method findById created for find exist item from array of items passing through method item's id.
     * @param id it is specific item's id that item's would you like to find out.
     * @return resultFindById it's concrete item that you find out
     */

    public Item findById(String id) {
        Item resultFindById = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                resultFindById = item;
                break;
            }
        }
        return resultFindById;
    }

    /**
     * Method findByName created for find exist item from array of items passing through method item's name.
     * @param name it is specific item's id that item's would you like to find out.
     * @return resultFindByName it's concrete item that you find out
     */

    public Item findByName(String name) {
        Item resultFindByName = null;
        for (Item item : items) {
            if (item != null && item.getName().equals(name)) {
                resultFindByName = item;
            }
        }
        return resultFindByName;
    }

    /**
     * Method findByyDate created for find exist item from array of items passing through method item's created date.
     * @param create it is concrete item's created date that item's would you like to find out.
     * @return resultFindByyDate it's concrete item that you find out
     */

    public Item findByDate(long create) {
        Item resultFindByDate = null;
        for (Item item : items) {
            if (item != null && item.getCreate() == (create)) {
                resultFindByDate = item;
            }
        }
        return resultFindByDate;
    }

    /**
     * Method generateId created for generate items id.
     * @return generated id number
     */

    private String generateId() {
        final int idDev = 1_000_000;
        return String.valueOf(Math.abs(rm.nextInt() / idDev));
    }

    /**
     * Method getAll created for getting all of exist item from array of items.
     * @return itemsDelete array without deleted item
     */

    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Method addComment using for adding comment to the item.
     * @param item it is item that you want  to comment
     * @param comment it is comment that you passing to the method to add to your item
     */

    public void addComment(Item item, String comment) {
        item.addComment(comment);
    }




}