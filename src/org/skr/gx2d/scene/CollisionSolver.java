package org.skr.gx2d.scene;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by rat on 31.10.14.
 */
public class CollisionSolver implements ContactListener {

    private static final int BI_CACHE_SIZE = 2048;

    Scene scene;
//    BodyItem [] bodyItems = new BodyItem[ BI_CACHE_SIZE ];
    int bodyItemsNumber = 0;

    public CollisionSolver(Scene scene) {
        this.scene = scene;
    }

    public void reset() {
        bodyItemsNumber = 0;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

//        BodyItem biA = (BodyItem) contact.getFixtureA().getBody().getUserData();
//        BodyItem biB = (BodyItem) contact.getFixtureB().getBody().getUserData();
//        float [] normalIs = impulse.getNormalImpulses();
//        float [] tangentIs = impulse.getTangentImpulses();
//        float totalImpulse = 0;
//        for ( int i = 0; i < impulse.getCount(); i++) {
//            totalImpulse += normalIs[i] + tangentIs[i];
//        }
//
//
//        biA.addImpulse( totalImpulse );
//        biB.addImpulse( totalImpulse );
//
//        if ( bodyItemsNumber >= (BI_CACHE_SIZE) )
//            return;
//        bodyItems[bodyItemsNumber++] = biA;
//        if ( bodyItemsNumber >= (BI_CACHE_SIZE) )
//            return;
//        bodyItems[bodyItemsNumber++] = biB;
    }

    public void runScripts() {
//        for ( int i = 0; i < bodyItemsNumber; i++ ) {
//            bodyItems[i].getOnCollisionSlots().run();
//        }
    }

}
