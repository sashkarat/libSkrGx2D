package org.skr.gx2d.script.dts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.skr.SkrScript.*;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.script.NodeScriptEE;

/**
 * Created by rat on 14.12.14.
 */
public class NodeScriptBodyHandler {

    public static final int E_DYN_BODY = NodeScriptEE.genAddr();
    public static final int E_KYN_BODY = NodeScriptEE.genAddr();
    public static final int E_STAT_BODY = NodeScriptEE.genAddr();


    public static void setup() {
        setupFunctions();
        setupProperties();
    }

    private static void setupProperties() {

        NodeScriptEE.setProperty(NodeScriptEE.PROP_TYPE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.set( bi.getBody().getType(), NodeScriptEE.DTS_ENUM );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        if (value.dts() == NodeScriptEE.DTS_ENUM) {
                            bi.getBody().setType((BodyDef.BodyType) value.val() );
                            return true;
                        }
                        bi.getBody().setType(BodyDef.BodyType.valueOf(value.asString()));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_POSITION,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.set(bi.getBody().getPosition(),NodeScriptEE.DTS_VECTOR2);
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        Vector2 v = (Vector2) value.val();
                        bi.getBody().setTransform( v, bi.getBody().getAngle() );
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANGLE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getAngle());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setTransform( bi.getBody().getPosition(), value.asFloat(rc) );
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LINEAR_VEL,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.set(bi.getBody().getLinearVelocity(), NodeScriptEE.DTS_VECTOR2 );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setLinearVelocity((Vector2) value.val() );
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANGULAR_VEL,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getAngle());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setAngularVelocity(value.asFloat(rc));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LINEAR_DAMPING,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getLinearDamping());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setLinearDamping(value.asFloat(rc));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANGULAR_DAMPING,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getAngularDamping());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        if ( !value.isNumber() )
                            return Engine.peSetPropInvalidType(NodeScriptEE.PROP_ANGULAR_DAMPING, rc);
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setAngularDamping(value.asFloat(rc));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ALLOW_SLEEP,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsBool( bi.getBody().isSleepingAllowed() );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setSleepingAllowed(value.asBool( rc ));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_AWAKE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsBool( bi.getBody().isAwake() );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setAwake(value.asBool( rc ));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_FIXED_ROT,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsBool( bi.getBody().isFixedRotation() );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setAwake(value.asBool( rc ));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_BULLET,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsBool( bi.getBody().isBullet() );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setBullet(value.asBool( rc ));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ACTIVE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsBool( bi.getBody().isActive() );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setActive(value.asBool( rc ) );
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_GRAVITY_SCALE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getGravityScale());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.getBody().setGravityScale(value.asFloat(rc));
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MASS,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getBody().getMass());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_MASS, rc);
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ABSORBED_IMPULSE,
                NodeScriptEE.DTS_BODY_HANDLER, new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        BodyHandler bi = (BodyHandler) obj.val();
                        res.setAsFloat(bi.getAbsorbedImpulse());
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        if ( !value.isNumber() )
                            return Engine.peSetPropInvalidType(NodeScriptEE.PROP_ABSORBED_IMPULSE, rc);
                        BodyHandler bi = (BodyHandler) obj.val();
                        bi.setAbsorbedImpulse(value.asFloat(rc));
                        return true;
                    }
                });
    }

    private static void setupFunctions() {

        NodeScriptEE.setBuildInFunction(E_DYN_BODY, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(BodyDef.BodyType.DynamicBody, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_KYN_BODY, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(BodyDef.BodyType.KinematicBody, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_STAT_BODY, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(BodyDef.BodyType.StaticBody, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });
    }




}
