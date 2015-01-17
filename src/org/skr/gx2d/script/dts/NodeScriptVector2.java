package org.skr.gx2d.script.dts;

import com.badlogic.gdx.math.Vector2;
import org.skr.SkrScript.*;
import org.skr.gx2d.script.NodeScriptEE;

/**
 * Created by rat on 14.12.14.
 */

public class NodeScriptVector2 {

    // === VECTOR2 FUNCTION CODES =========
    public static final int F_CRT_VEC          = NodeScriptEE.genAddr();
    public static final int F_VEC_ADD          = NodeScriptEE.genAddr();
    public static final int F_VEC_ANGLE        = NodeScriptEE.genAddr();
    public static final int F_VEC_CLAMP        = NodeScriptEE.genAddr();
    public static final int F_VEC_CROSS        = NodeScriptEE.genAddr();
    public static final int F_VEC_DOT          = NodeScriptEE.genAddr();
    public static final int F_VEC_DST          = NodeScriptEE.genAddr();
    public static final int F_VEC_DST2         = NodeScriptEE.genAddr();
    public static final int F_VEC_EPS_EQ       = NodeScriptEE.genAddr();
    public static final int F_VEC_HAS_OPP_DIR  = NodeScriptEE.genAddr();
    public static final int F_VEC_HAS_SAME_DIR = NodeScriptEE.genAddr();
    public static final int F_VEC_IS_COLLINEAR = NodeScriptEE.genAddr();
    public static final int F_VEC_IS_ONLINE    = NodeScriptEE.genAddr();
    public static final int F_VEC_IS_PERPENDIC = NodeScriptEE.genAddr();
    public static final int F_VEC_IS_UNIT      = NodeScriptEE.genAddr();
    public static final int F_VEC_IS_ZERO      = NodeScriptEE.genAddr();
    public static final int F_VEC_LEN          = NodeScriptEE.genAddr();
    public static final int F_VEC_LEN2         = NodeScriptEE.genAddr();
    public static final int F_VEC_LERP         = NodeScriptEE.genAddr();
    public static final int F_VEC_LIMIT        = NodeScriptEE.genAddr();
    public static final int F_VEC_LIMIT2       = NodeScriptEE.genAddr();
    public static final int F_VEC_NOR          = NodeScriptEE.genAddr();
    public static final int F_VEC_ROT          = NodeScriptEE.genAddr();
    public static final int F_VEC_SCL          = NodeScriptEE.genAddr();
    public static final int F_VEC_SET          = NodeScriptEE.genAddr();
    public static final int F_VEC_SET_ANG      = NodeScriptEE.genAddr();
    public static final int F_VEC_SET_LEN      = NodeScriptEE.genAddr();
    public static final int F_VEC_SET_LEN2     = NodeScriptEE.genAddr();
    public static final int F_VEC_SET_ZERO     = NodeScriptEE.genAddr();
    public static final int F_VEC_SUB          = NodeScriptEE.genAddr();




    public static void setup() {
        setupFunctions();
        setupProperties();
    }

    private static void setupProperties() {
        NodeScriptEE.setProperty(NodeScriptEE.PROP_X, NodeScriptEE.DTS_VECTOR2,
                new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        res.setAsFloat( ( (Vector2) obj.val() ).x );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        ((Vector2) obj.val()).x = value.asFloat(rc);
                        return true;
                    }
                });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_Y, NodeScriptEE.DTS_VECTOR2,
                new PropertyPool.Adapter() {
                    @Override
                    public boolean get(Value obj, Value res, RunContext rc) {
                        res.setAsFloat( ( (Vector2) obj.val() ).y );
                        return true;
                    }

                    @Override
                    public boolean set(Value obj, Value value, RunContext rc) {
                        ((Vector2) obj.val()).y = value.asFloat(rc);
                        return true;
                    }
                });
    }


    private static void setupFunctions() {

        NodeScriptEE.setBuildInFunction(F_CRT_VEC, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecCreate(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_ADD, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecAdd(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_ANGLE, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecAngle(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_CLAMP, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecClamp(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_CROSS, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecCross(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_DOT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecDot(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_DST, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecDst(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_DST2, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecDst2(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_EPS_EQ, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecEpsilonEqual(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_HAS_OPP_DIR, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecHasOppositeDirection(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_HAS_SAME_DIR, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecHasSameDirection(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_IS_COLLINEAR, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecIsCollinear(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_IS_ONLINE, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecIsOnline(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_IS_PERPENDIC, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecIsPerpendicular(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_IS_UNIT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecIsUnit(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_IS_ZERO, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecIsZero(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_LEN, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecLen(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_LEN2, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecLen2(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_LERP, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecLerp(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_LIMIT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecLimit(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_LIMIT2, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecLimit2(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_NOR, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecNor(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_ROT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecRotate(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SCL, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecScale(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SET, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSet(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SET_ANG, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSetAngle(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SET_LEN, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSetLen(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SET_LEN2, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSetLen2(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SET_ZERO, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSetZero(args, numOfArgs, res, rc);
            }
        });

        NodeScriptEE.setBuildInFunction(F_VEC_SUB, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                return vecSub(args, numOfArgs, res, rc);
            }
        });
    }

    private static boolean vecSub(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.set( v.sub( v2 ), NodeScriptEE.DTS_VECTOR2 );
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.set( v.sub(x,y), NodeScriptEE.DTS_VECTOR2 );
            return true;
        }
        return false;
    }

    private static boolean vecSetZero(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        res.set( v.setZero(), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecSetLen2(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float l = args.get(1).asFloat(rc);
        res.set( v.setLength2(l), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecSetLen(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float l = args.get(1).asFloat(rc);
        res.set( v.setLength(l), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecSetAngle(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float a = args.get(1).asFloat(rc);
        res.set( v.setAngle(a), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecSet(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.set( v.set(v2), NodeScriptEE.DTS_VECTOR2 );
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.set( v.set(x, y), NodeScriptEE.DTS_VECTOR2 );
            return true;
        }
        return false;
    }

    private static boolean vecScale(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.set( v.scl( v2 ), NodeScriptEE.DTS_VECTOR2 );
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.set( v.scl(x,y), NodeScriptEE.DTS_VECTOR2 );
            return true;
        }
        return false;
    }

    private static boolean vecRotate(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float a = args.get(1).asFloat(rc);
        res.set( v.rotate(a), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecNor(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        res.set( v.nor(), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecLimit2(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float l = args.get(1).asFloat(rc);
        res.set( v.limit2(l), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecLimit(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float l = args.get(1).asFloat(rc);
        res.set( v.limit(l), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecLerp(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Vector2 vt = (Vector2) args.get(1).val();
        Float alpha = args.get(2).asFloat(rc);
        res.set( v.lerp( vt, alpha ), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecLen2(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        res.setAsFloat(v.len2());
        return true;
    }

    private static boolean vecLen(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        res.setAsFloat(v.len());
        return true;
    }

    private static boolean vecIsZero(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 1  ) {
            Vector2 v = (Vector2) args.get(0).val();
            res.set( v.isZero(), Def.DTS_BOOL );
            return true;
        } else if ( numOfArgs == 2 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float m = args.get(1).asFloat(rc);
            res.set( v.isZero( m ), Def.DTS_BOOL );
            return true;
        }
        return false;
    }

    private static boolean vecIsUnit(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 1  ) {
            Vector2 v = (Vector2) args.get(0).val();
            res.setAsBool(v.isUnit());
            return true;
        } else if ( numOfArgs == 2 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float m = args.get(1).asFloat(rc);
            res.setAsBool(v.isUnit(m));
            return true;
        }
        return false;
    }

    private static boolean vecIsPerpendicular(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsBool(v.isPerpendicular(v2));
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            Float e = args.get(2).asFloat(rc);
            res.setAsBool(v.isPerpendicular(v2, e));
            return true;
        }
        return false;
    }

    private static boolean vecIsOnline(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.set( v.isOnLine(v2), Def.DTS_BOOL );
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            Float e = args.get(2).asFloat(rc);
            res.setAsBool(v.isOnLine(v2, e));
            return true;
        }
        return false;
    }

    private static boolean vecIsCollinear(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsBool(v.isCollinear(v2));
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            Float e = args.get(2).asFloat(rc);
            res.setAsBool(v.isCollinear(v2, e));
            return true;
        }
        return false;
    }

    private static boolean vecHasSameDirection(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Vector2 v2 = (Vector2) args.get(1).val();
        res.setAsBool(v.hasSameDirection(v2));
        return true;
    }

    private static boolean vecHasOppositeDirection(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Vector2 v2 = (Vector2) args.get(1).val();
        res.setAsBool(v.hasOppositeDirection(v2));
        return true;
    }

    private static boolean vecEpsilonEqual(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 3  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            Float e = args.get(2).asFloat(rc);
            res.setAsBool(v.epsilonEquals(v2, e));
            return true;
        } else if ( numOfArgs == 4 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            Float e = args.get(3).asFloat(rc);
            res.setAsBool(v.epsilonEquals(x, y, e));
            return true;
        }
        return false;
    }

    private static boolean vecDst2(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 3  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsFloat(v.dst2(v2));
            return true;
        } else if ( numOfArgs == 4 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.setAsFloat(v.dst2(x, y));
            return true;
        }
        return false;
    }

    private static boolean vecDst(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 3  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsFloat(v.dst(v2));
            return true;
        } else if ( numOfArgs == 4 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.setAsFloat(v.dst(x, y));
            return true;
        }
        return false;
    }

    private static boolean vecDot(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 3  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsFloat(v.dot(v2));
            return true;
        } else if ( numOfArgs == 4 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.setAsFloat(v.dot(x, y));
            return true;
        }
        return false;
    }

    private static boolean vecCross(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 3  ) {
            Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.setAsFloat(v.crs(v2));
            return true;
        } else if ( numOfArgs == 4 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.setAsFloat(v.crs(x, y));
            return true;
        }
        return false;
    }

    private static boolean vecClamp(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        Float min = args.get(1).asFloat(rc);
        Float max = args.get(2).asFloat(rc);
        res.set( v.clamp( min, max), NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

    private static boolean vecAngle(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Vector2 v = (Vector2) args.get(0).val();
        res.setAsFloat(v.angle());
        return true;
    }

    private static boolean vecAdd(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        if ( numOfArgs == 2  ) {
           Vector2 v = (Vector2) args.get(0).val();
            Vector2 v2 = (Vector2) args.get(1).val();
            res.set( v.add(v2), NodeScriptEE.DTS_VECTOR2 );
            return true;
        } else if ( numOfArgs == 3 ) {
            Vector2 v = (Vector2) args.get(0).val();
            Float x = args.get(1).asFloat(rc);
            Float y = args.get(2).asFloat(rc);
            res.set(v.add(x, y), NodeScriptEE.DTS_VECTOR2);
            return true;
        }
        return false;
    }

    protected static boolean vecCreate(ValuePool args, int numOfArgs, Value res, RunContext rc) {
        Float x = args.get(0).asFloat(rc);
        Float y = args.get(1).asFloat(rc);
        res.set( new Vector2( x, y) , NodeScriptEE.DTS_VECTOR2 );
        return true;
    }

}
