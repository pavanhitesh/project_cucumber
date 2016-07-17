package com.pavan.cvsreader;


import java.lang.reflect.Constructor;
import java.util.Hashtable;

/**
 * Class to creates the page objects .Creates page objects and puts it in a hash
 * map so that writers need not call new explicitly on the page object class
 *
 * @author Hitesh
 */
public class PageFactory {
    public static Hashtable<Object, Object> objects = new Hashtable<Object, Object>();

    public synchronized static Object getNewObject(Class key) throws Exception {
        return (Object) constructNewObject(key);
    }

    public synchronized static Object constructNewObject(Class key)
            throws Exception {

        Object object = objects.get(key.getCanonicalName());

        if (object == null) {
            Constructor[] constructor = key.getConstructors();

            object = constructor[0].newInstance();

            objects.put(key, object);
            // System.out.println(objects);
        }

        return object;
    }

    /*
     * public static void main(String[] args) {
     *
     * try { getNewObject(LoginModuleAction.class); } catch (Exception e) { //
     * TODO Auto-generated catch block e.printStackTrace(); } }
     */
}