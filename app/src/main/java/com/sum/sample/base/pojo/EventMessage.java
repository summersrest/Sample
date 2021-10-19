package com.sum.sample.base.pojo;

/**
 * @author liujiang
 * Desc: EventBus Vo
 */
public class EventMessage<T> {
    private int event;
    private T t;

    public EventMessage(int event) {
        this.event = event;
    }

    public EventMessage(int event, T t) {
        this.event = event;
        this.t = t;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
} 
