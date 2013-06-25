package com.github.marceloverdijk.springappengine.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

    public Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
