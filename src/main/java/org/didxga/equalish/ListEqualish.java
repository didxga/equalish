package org.didxga.equalish;

import java.util.*;

public class ListEqualish {

    public static <T> boolean equalish(List<T> a, List<T> b, boolean honorOrder) {
        if(a == null || b == null || a.size() != b.size()) {
            return false;
        }

        if(honorOrder) {
            Iterator<T> ait = a.iterator();
            Iterator<T> bit = b.iterator();

            while (ait.hasNext()) {
                T at = ait.next();
                T bt = bit.next();

                if (at == null || bt == null) {
                    return false;
                }
                else if (!at.getClass().getCanonicalName().equals(bt.getClass().getCanonicalName())) {
                    return false;
                }
                else if (at instanceof List) {
                    if(!ListEqualish.equalish((List)at, (List)bt, true)) {
                        return false;
                    }
                }
                else if (at instanceof Map) {
                    if(!ListEqualish.equalish((Map)at, (Map)bt, true)) {
                        return false;
                    }
                }

                if (!at.equals(bt)) {
                    return false;
                }
            }
        } else {
            int i;
            int j;
            Map<Integer, Boolean> keyRecorder = new HashMap<>();
            int l = a.size();
            for(i=0; i<l; i++) {
                if(a.get(i) instanceof List) {
                    List al = (List) a.get(i);
                    for(j=0; j<l; j++) {
                        if(keyRecorder.containsKey(j)) {
                            continue;
                        }
                        if(b.get(j) instanceof List) {
                           List bl =  (List) b.get(j);
                           if(al.size() == bl.size() && keyRecorder.get(j) == null) {
                               keyRecorder.put(j, true);
                               if(!ListEqualish.equalish(al, bl, false)) {
                                   keyRecorder.remove(j);
                               } else {
                                   break;
                               }
                           }
                        }
                    }
                    if(keyRecorder.size()!=i+1) {
                        return false;
                    }
                } else if(a.get(i) instanceof Map) {
                    Map am = (Map) a.get(i);
                    for(j=0; j<l; j++) {
                        if(keyRecorder.containsKey(j)) {
                            continue;
                        }
                        if(b.get(j) instanceof Map) {
                            Map bm =  (Map) b.get(j);
                            if(am.size() == bm.size() && keyRecorder.get(j) == null) {
                                keyRecorder.put(j, true);
                                if(!ListEqualish.equalish(am, bm, false)) {
                                    keyRecorder.remove(j);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    if(keyRecorder.size()!=i+1) {
                        return false;
                    }
                } else {
                    for(j=0; j<l; j++) {
                        if(keyRecorder.containsKey(j)) {
                            continue;
                        }
                        keyRecorder.put(j, true);
                        if(!a.get(i).equals(b.get(j))) {
                            keyRecorder.remove(j);
                        } else {
                            break;
                        }
                    }
                    if(keyRecorder.size()!=i+1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static <K, V> boolean equalish(Map<K, V> a, Map<K, V> b, boolean honorOrder) {
        if(a == null || b == null || a.size() != b.size()) {
            return false;
        }

        Set<Map.Entry<K, V>> aentry =  a.entrySet();
        Set<Map.Entry<K, V>> bentry =  b.entrySet();
        Iterator<Map.Entry<K, V>> ait = aentry.iterator();
        Iterator<Map.Entry<K, V>> bit = bentry.iterator();

        if(honorOrder) {
            while (ait.hasNext()) {
                Map.Entry<K, V> ae = ait.next();
                Map.Entry<K, V> be = bit.next();

                if(!ae.getKey().equals(be.getKey())) {
                    return false;
                }
                else if(!ae.getValue().getClass().getCanonicalName().equals(be.getKey().getClass().getCanonicalName())) {
                    return false;
                }
                else if(ae.getValue() instanceof List) {
                    if(!ListEqualish.equalish((List)ae.getValue(), (List)be.getValue(), true)) {
                        return false;
                    }
                }
                else if(ae.getValue() instanceof  Map) {
                    if(!ListEqualish.equalish((Map)ae.getValue(), (Map)be.getValue(), true)) {
                        return false;
                    }
                } else {
                    if(!ae.getValue().equals(be.getValue())) {
                        return false;
                    }
                }
            }
        } else {
            Iterator<K> ak = a.keySet().iterator();
            while(ak.hasNext()) {
                K k = ak.next();
                if(b.get(k) == null) {
                    return false;
                }
                if(!a.get(k).getClass().getCanonicalName().equals(b.get(k).getClass().getCanonicalName())) {
                    return false;
                } else if(a.get(k) instanceof List) {
                    if(!(b.get(k) instanceof List)) {
                        return false;
                    } else {
                        if(!ListEqualish.equalish((List)a.get(k), (List)b.get(k), false)) {
                            return false;
                        }
                    }
                } else if(a.get(k) instanceof Map) {
                    if(!(b.get(k) instanceof Map)) {
                        return false;
                    } else {
                        if(!ListEqualish.equalish((Map)a.get(k), (Map)b.get(k), false)) {
                            return false;
                        }
                    }
                } else {
                    if(!b.get(k).equals(a.get(k))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static <T> boolean equalish(List<T> a, List<T> b) {
        return ListEqualish. equalish(a, b,true);
    }
}
