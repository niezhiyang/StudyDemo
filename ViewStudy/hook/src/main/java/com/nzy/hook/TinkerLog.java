package com.nzy.hook;

public class TinkerLog {
    private static final String TAG = "Tinker.TinkerLog";
    private static TinkerLogImp debugLog = new TinkerLogImp() {

        @Override
        public void v(final String tag, final String format, final Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            android.util.Log.v(tag, log);
        }

        @Override
        public void i(final String tag, final String format, final Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            android.util.Log.i(tag, log);

        }

        @Override
        public void d(final String tag, final String format, final Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            android.util.Log.d(tag, log);
        }

        @Override
        public void w(final String tag, final String format, final Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            android.util.Log.w(tag, log);
        }

        @Override
        public void e(final String tag, final String format, final Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            android.util.Log.e(tag, log);
        }

        @Override
        public void printErrStackTrace(String tag, Throwable tr, String format, Object... params) {
            String log = (params == null || params.length == 0) ? format : String.format(format, params);
            if (log == null) {
                log = "";
            }
            log += "  " + android.util.Log.getStackTraceString(tr);
            android.util.Log.e(tag, log);
        }
    };
    private static TinkerLogImp tinkerLogImp = debugLog;

    public static void setTinkerLogImp(TinkerLogImp imp) {
        tinkerLogImp = imp;
    }

    public static TinkerLogImp getImpl() {
        return tinkerLogImp;
    }

    public static void v(final String tag, final String msg, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.v(tag, msg, obj);
        }
    }

    public static void e(final String tag, final String msg, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.e(tag, msg, obj);
        }
    }

    public static void w(final String tag, final String msg, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.w(tag, msg, obj);
        }
    }

    public static void i(final String tag, final String msg, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.i(tag, msg, obj);
        }
    }

    public static void d(final String tag, final String msg, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.d(tag, msg, obj);
        }
    }

    public static void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj) {
        if (tinkerLogImp != null) {
            tinkerLogImp.printErrStackTrace(tag, tr, format, obj);
        }
    }

    public interface TinkerLogImp {

        void v(final String tag, final String msg, final Object... obj);

        void i(final String tag, final String msg, final Object... obj);

        void w(final String tag, final String msg, final Object... obj);

        void d(final String tag, final String msg, final Object... obj);

        void e(final String tag, final String msg, final Object... obj);

        void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj);

    }

}
