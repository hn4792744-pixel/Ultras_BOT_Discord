package com.ultras.bans.utility;
import java.util.Locale;
public final class DurationUtil {
 private DurationUtil(){}
 public static long parse(String s){ if(s==null||s.isBlank()) return -1; try { String x=s.toLowerCase(Locale.ROOT).trim(); long n=Long.parseLong(x.substring(0,x.length()-1)); return switch(x.charAt(x.length()-1)){case 's'->n;case 'm'->n*60;case 'h'->n*3600;case 'd'->n*86400;case 'w'->n*604800;default->-1;}; }catch(Exception e){return -1;} }
 public static String format(long seconds){ if(seconds<=0)return "Permanent"; long d=seconds/86400; seconds%=86400; long h=seconds/3600; seconds%=3600; long m=seconds/60; if(d>0)return d+"d"+(h>0?" "+h+"h":""); if(h>0)return h+"h"+(m>0?" "+m+"m":""); return Math.max(1,m)+"m"; }
}
