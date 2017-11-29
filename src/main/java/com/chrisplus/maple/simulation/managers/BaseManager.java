package com.chrisplus.maple.simulation.managers;

import com.chrisplus.maple.simulation.entities.BaseEntity;
import com.chrisplus.maple.simulation.enums.GameTagOuterClass.GameTag;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseManager {

  protected HashMap<GameTag, String> dataMap;
  protected List<BaseObserver> observers;
  protected WeakReference<BaseEntity> obj;

  public BaseManager(BaseEntity entity) {
    this.obj = new WeakReference(entity);
    this.observers = new ArrayList<>();
    dataMap = createDataMap();

  }

  abstract protected HashMap<GameTag, String> createDataMap();

  public void registerObserver(BaseObserver observer) {
    if (observer == null || observers == null) {
      throw new IllegalArgumentException();
    }

    observers.add(observer);
  }

  public void unregisterObserver(BaseObserver observer) {
    if (observer == null || observers == null) {
      throw new IllegalArgumentException();
    }

    if (observers.contains(observer)) {
      observers.remove(observer);
    }
  }

  public Integer getAttribute(GameTag tag) {
    if (dataMap == null || !dataMap.containsKey(tag)) {
      return null;
    }

    String fieldName = dataMap.get(tag);
    try {
      Field field = obj.getClass().getField(fieldName);
      return field.getInt(obj);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
      return null;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Boolean setAttribute(GameTag tag, Integer val){
    if(dataMap == null || !dataMap.containsKey(tag)){
      return false;
    }

    String fieldName = dataMap.get(tag);
    try {
      Field field = obj.getClass().getField(fieldName);
      field.setInt(obj, val);
      return true;

    } catch (NoSuchFieldException e) {
      e.printStackTrace();
      return false;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return false;
    }
  }


}
