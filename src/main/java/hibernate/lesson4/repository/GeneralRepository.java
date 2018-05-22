package hibernate.lesson4.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 15.12.2017.
 */

public abstract class GeneralRepository<T> {

    private SessionFactory sessionFactory;

    public T save(T t) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
            return t;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Save is failed");
        }
    }
    public T update(T t) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
            return t;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Update is failed");
        }
    }

    public T delete(String hqlFindEntity, String hqlDelEntity, long id){
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            T deleteEntity = findById(hqlFindEntity, id);

            Query queryDelHt = session.createQuery(hqlDelEntity);
            queryDelHt.setParameter("ID", id);
            queryDelHt.executeUpdate();

            tr.commit();

            return deleteEntity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Delete is failed");
        }
    }

    public T findById(String sql, Long id){
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(sql);
            query.setParameter("ID", id);

            if (query.uniqueResult() == null)
                return null;
            T entity = (T) query.getSingleResult();

            return entity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public SessionFactory createSessionFactory() {

        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
    /*public T addEntity(T t) throws Exception {

        if (t == null)
            throw new Exception("You enter wrong data");
      //  generateId();

        StringBuffer bf = new StringBuffer("");
        String string = t.toString();

        bf.append("\r\n");
        bf.append(string);

        writeToFile(bf);

        return t;
    }
    public void deleteEntity(Long id) throws Exception {
        if (id == null)
            throw new Exception("You didn't enter any data");

        ArrayList<T> entityArrayListBeforeDel = entityToArrayList();
        StringBuffer res = new StringBuffer();
        StringBuffer resBkp = new StringBuffer();
        int count = 0;
        int index = 0;
        for (T t : entityArrayListBeforeDel) {

            resBkp.append(t.toString());
            resBkp.append("\r\n");

            String str = entityArrayListBeforeDel.get(index).toString();
            String[] fields = str.split(",");
            if (Long.parseLong(fields[0]) != id) {
                res.append(t.toString());
                res.append("\r\n");
            }
            if (Long.parseLong(fields[0]) == id) {
                count++;
            }
            index++;
        }
        res.delete(res.length() - 2, res.length());
        if (count < 1) {
            throw new Exception("Entity with id " + id + " does not exist in DB");
        }

        try {
            writeToCleanFile(res);
        } catch (IOException e) {
            writeToCleanFile(resBkp);
            throw new IOException("Can't write to file " + pathDB);
        }
    }*/

    /*public  T findEntityById(Long idFind) throws Exception {
        checkId(idFind);
        ArrayList<T> entities = entityToArrayList();
        int index = 0;
        for (T t : entities) {
            String str = entities.get(index).toString();
            String[] fields = str.split(",");
            if (Long.parseLong(fields[0]) == idFind) {
                return t;
            }
            index++;
        }
        return null;
    }*/


   /* public static Long generateId() {
        long generateId;
        Random random = new Random();
        generateId = Math.abs(random.nextLong());
       //??? как обойти
       /*  while (checkPresenceId(generateId)) {
           generateId = Math.abs(random.nextLong());
        }*/
        /*return generateId;
    }

    public boolean checkPresenceId(long idEntity) throws Exception {
        checkId(idEntity);
        ArrayList<T> entityToArrayList = entityToArrayList();
        for (int i = 0; i < entityToArrayList.size(); i++) {
            String str = entityToArrayList.get(i).toString();
            String[] fields = str.split(",");
            if (Long.parseLong(fields[0]) == idEntity) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<T> entityToArrayList() throws Exception {
        ArrayList<T> arrayList = new ArrayList<>();
        StringBuffer stringBuffer = readFile();
        String strings = stringBuffer.toString();
        String[] strs = strings.split("\r\n");
        for (String el : strs) {
            String[] els = el.split(",");
            T entity = (T) formEntity(els);
            arrayList.add(entity);
        }

        return arrayList;
    }

    public abstract T formEntity(String[] str) throws Exception;


    public StringBuffer readFile() throws Exception {
        if (pathDB == null)
            throw new Exception("Path doesn't exist");
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(pathDB))) {
            String line;
            int numberLine = 0;
            while ((line = br.readLine()) != null) {
                numberLine++;
                checkLine(line, numberLine);
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }
            stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("Reading from filed " + pathDB + " faild");
        }
        return stringBuffer;
    }

    public void writeToFile(StringBuffer contentToWrite) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathDB, true))) {
            bufferedWriter.append(contentToWrite);

        } catch (IOException e) {
            throw new IOException("Can't write to file " + pathDB);
        }
    }

    public void writeToCleanFile(StringBuffer contentToWrite) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathDB, false))) {
            bw.append(contentToWrite);
        } catch (IOException e) {
            throw new IOException("Can't write to file " + pathDB);
        }
    }

    private static boolean checkId(Long id) throws Exception {
        if (id == null || id <= 0)
            throw new Exception("Id " + id + " is wrong");
        return true;
    }

    public abstract boolean checkLine(String line, int numberLine) throws Exception;*/

}
