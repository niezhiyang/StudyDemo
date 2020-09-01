package com.nzy.gameover;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class VideoEffectView extends FrameLayout {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    public static final int TIME_OUT = 10000;//10秒超时
    private IEffectPlayer player;
    private TextureView mTextureView;
    private boolean isStart;
    private OnVideoCompleteListener onVideoCompleteListener;
    private final String TAG = "VideoEffectView@" + hashCode();
    private List<ObjectAnimator> animatorList;
    private List<OnStartPlaySvgaAnim> onStartPlaySvgaAnims;
    private View pendingAddCoverView;
    private List<String> downloadingEffect = new ArrayList<>();

    public VideoEffectView(@NonNull Context context) {
        super(context);
    }

    public VideoEffectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoEffectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VideoEffectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnVideoCompleteListener(OnVideoCompleteListener videoCompleteListener) {
        this.onVideoCompleteListener = videoCompleteListener;
    }

//    /**
//     * 播放动画
//     *
//     * @param giftBean 需要使用到 avatarUrl，senderName，giftEffect
//     */
//    public void showGiftAnim(ContinuityGiftPlayBean giftBean) {
//        if (giftBean == null || giftBean.getGiftEffect() == null) {
//            onError();
//            return;
//        }
//        showGiftAnim(giftBean.getGiftEffect(), getStringList(giftBean.getAvatarUrl()), getStringList(giftBean.getSenderName()));
//    }

//    /**
//     * @param giftEffect
//     * @param avatarList 需要显示的头像url集合
//     * @param nameList   需要显示的文字内容集合
//     */
//    public void showGiftAnim(final GiftEffect giftEffect, final List<String> avatarList, final List<String> nameList) {
//        if (giftEffect == null || giftEffect.isNotValid()) {
//            onError();
//            return;
//        }
//        if (GiftVideoEffectResourceHelper.getInstance().isResourceReady(giftEffect.getResourceId())) {//本地有资源就直接播
//            readConfigJsonFile(avatarList, nameList, new File(GiftVideoEffectResourceHelper.getInstance().getBaseFile(), giftEffect.getResourceId()), getEffectType(giftEffect));
//        } else {//去下载资源再播
//            //给个倒计时限制
//            MomoMainThreadExecutor.postDelayed(getTaskTag(), timeoutRunnable, TIME_OUT);
//            GiftVideoEffectResourceHelper.getInstance().downloadResource(giftEffect.getResourceId(), giftEffect.getResourceUrl(), new BaseDownloadResourceHelper.OnPrepareListener() {
//                @Override
//                public void prepared(File file) {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    readConfigJsonFile(avatarList, nameList, file, getEffectType(giftEffect));
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//
//                @Override
//                public void onFail() {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    onError();
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//            });
//            downloadingEffect.add(giftEffect.getResourceId());
//        }
//    }

//    /**
//     * 和svga同时播放
//     */
//    public void showGiftAnim(final GiftEffect giftEffect, final OnStartPlaySvgaAnim onStartPlaySvgaAnim) {
//        if (giftEffect == null || giftEffect.isNotValid()) {
//            onError();
//            return;
//        }
//        if (GiftVideoEffectResourceHelper.getInstance().isResourceReady(giftEffect.getResourceId())) {//本地有资源就直接播
//            readConfigJsonFile(new File(GiftVideoEffectResourceHelper.getInstance().getBaseFile(), giftEffect.getResourceId()), getEffectType(giftEffect), onStartPlaySvgaAnim);
//        } else {//去下载资源再播
//            //给个倒计时限制
//            MomoMainThreadExecutor.postDelayed(getTaskTag(), timeoutRunnable, TIME_OUT);
//            GiftVideoEffectResourceHelper.getInstance().downloadResource(giftEffect.getResourceId(), giftEffect.getResourceUrl(), new BaseDownloadResourceHelper.OnPrepareListener() {
//                @Override
//                public void prepared(File file) {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    readConfigJsonFile(file, getEffectType(giftEffect), onStartPlaySvgaAnim);
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//
//                @Override
//                public void onFail() {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    onError();
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//            });
//            downloadingEffect.add(giftEffect.getResourceId());
//        }
//    }

//    public void showGiftAnim(final GiftEffect giftEffect) {
//        if (giftEffect == null || giftEffect.isNotValid()) {
//            onError();
//            return;
//        }
//        if (GiftVideoEffectResourceHelper.getInstance().isResourceReady(giftEffect.getResourceId())) {//本地有资源就直接播
//            readConfigJsonFile(new File(GiftVideoEffectResourceHelper.getInstance().getBaseFile(), giftEffect.getResourceId()), getEffectType(giftEffect));
//        } else {//去下载资源再播
//            //给个倒计时限制
//            MomoMainThreadExecutor.postDelayed(getTaskTag(), timeoutRunnable, TIME_OUT);
//            GiftVideoEffectResourceHelper.getInstance().downloadResource(giftEffect.getResourceId(), giftEffect.getResourceUrl(), new BaseDownloadResourceHelper.OnPrepareListener() {
//                @Override
//                public void prepared(File file) {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    readConfigJsonFile(file, getEffectType(giftEffect));
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//
//                @Override
//                public void onFail() {
//                    MomoMainThreadExecutor.cancelSpecificRunnable(getTaskTag(), timeoutRunnable);
//                    onError();
//                    downloadingEffect.remove(giftEffect.getResourceId());
//                }
//            });
//            downloadingEffect.add(giftEffect.getResourceId());
//        }
//    }

    public boolean isStart() {
        return isStart;
    }

//    public void destroy() {
//        stopPlayer();
//        GiftVideoEffectResourceHelper.getInstance().cancelAllTask();
//    }

    /**
     * 备用方法 想播放asset下文件可以调用
     */
    public void showGiftAnim(String assetFileName, int type) {
        AssetFileDescriptor fd;
        try {
            fd = getResources().getAssets().openFd(assetFileName);
            startVideoEffectAnimation("", fd, type);
        } catch (IOException e) {
            e.printStackTrace();
            onError();
        }
    }

//    /**
//     * 获取视频特效类型
//     *
//     * @param giftEffect
//     * @return
//     */
//    private int getEffectType(GiftEffect giftEffect) {
//        switch (giftEffect.getResourceType()) {
//            case 1://alpha-beta
//                return MERGE_ALPHA;
//            case 2://绿屏抠像
//                return GREEN_SCREEN_KEYING;
//            default:
//                return 0;
//        }
//    }
//
//    private void readConfigJsonFile(final File file, final int type) {
//        File configFile = new File(file, "config.json");
//        if (configFile.exists()) {
//            try {
//                GiftVideoEffectResourceHelper.getInstance().getConfigJson(file.getName(), configFile, new GiftVideoEffectResourceHelper.OnGetConfigJsonListener() {
//                    @Override
//                    public void success(JSONObject configJson) {
//                        //播放视频
//                        parseVideoInfoAndPlay(configJson, file, type);
//                    }
//
//                    @Override
//                    public void fail() {
//                        onError();
//                    }
//                });
//            } catch (Exception e) {
//                Log.printErrStackTrace(getTaskTag(), e);
//                onError();
//            }
//        } else {
//            Log.e(TAG, "json文件不存在");
//            onError();
//        }
//    }

//    private void readConfigJsonFile(final List<String> avatarList, final List<String> nameList, final File file, final int type) {
//        File configFile = new File(file, "config.json");
//        if (configFile.exists()) {
//            try {
//                GiftVideoEffectResourceHelper.getInstance().getConfigJson(file.getName(), configFile, new GiftVideoEffectResourceHelper.OnGetConfigJsonListener() {
//                    @Override
//                    public void success(JSONObject configJson) {
//                        //播放视频
//                        parseVideoInfoAndPlay(configJson, file, type);
//                        //生成头像和名字动画
//                        buildAvatarAndNameAnimatorList(configJson, avatarList, nameList, file, configJson.optBoolean("isAvatarBelowVideo"));
//                    }
//
//                    @Override
//                    public void fail() {
//                        onError();
//                    }
//                });
//            } catch (Exception e) {
//                Log.printErrStackTrace(getTaskTag(), e);
//                onError();
//            }
//        } else {
//            Log.e(TAG, "json文件不存在");
//            onError();
//        }
//    }

//    private void readConfigJsonFile(final File file, final int type, final OnStartPlaySvgaAnim onStartPlaySvgaAnim) {
//        File configFile = new File(file, "config.json");
//        if (configFile.exists()) {
//            try {
//                GiftVideoEffectResourceHelper.getInstance().getConfigJson(file.getName(), configFile, new GiftVideoEffectResourceHelper.OnGetConfigJsonListener() {
//                    @Override
//                    public void success(final JSONObject configJson) {
//                        parseSvgaAndVideoInfoAndPlay(configJson, file, type, onStartPlaySvgaAnim);
//                    }
//
//                    @Override
//                    public void fail() {
//                        onError();
//                    }
//                });
//            } catch (Exception e) {
//                Log.printErrStackTrace(getTaskTag(), e);
//                onError();
//            }
//        } else {
//            Log.e(TAG, "json文件不存在");
//            onError();
//        }
//    }

//
//    private Runnable timeoutRunnable = new Runnable() {
//        @Override
//        public void run() {
//            onError();
//        }
//    };

//    /**
//     * @param avatarUrlList   头像url集合
//     * @param nameContentList 文字内容集合
//     */
//    private void buildAvatarAndNameAnimatorList(JSONObject configJson, List<String> avatarUrlList, List<String> nameContentList, File file, boolean isAvatarBelowVideo) {
//        if (animatorList == null) {
//            animatorList = new ArrayList<>();
//        } else {
//            animatorList.clear();
//        }
//        pendingAddCoverView = null;
//        int videoWidth = UIUtils.getScreenWidth();
//        int videoHeight = videoWidth * 16 / 9;
//        //读取和生成蒙层动画
//        parseCoverLayout(configJson);
//        //读取和生成头像动画
//        parseAvatarAnim(configJson, avatarUrlList, videoWidth, videoHeight, isAvatarBelowVideo);
//        //读取和生成名字动画
//        parseNameAnim(configJson, nameContentList, videoWidth, videoHeight, file, isAvatarBelowVideo);
//    }

//    private void parseCoverLayout(JSONObject configJson) {
//        VideoEffectCoverAnim cover = GsonUtils.g().fromJson(configJson.optString("cover"), VideoEffectCoverAnim.class);
//        if (cover != null) {
//            View coverLayout = new View(getContext());
//            coverLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
//            coverLayout.setBackgroundColor(ColorUtils.parseAllColor(cover.getColor(), Color.TRANSPARENT));
//            coverLayout.setAlpha(0f);
//            pendingAddCoverView = coverLayout;
//            ObjectAnimator animator = ObjectAnimator.ofFloat(coverLayout, ALPHA, 0f, cover.getAlpha()).setDuration(cover.getDuration());
//            animator.setStartDelay(cover.getTime());
//            animatorList.add(animator);
//        }
//    }
//
//    private void parseAvatarAnim(JSONObject configJson, List<String> avatarUrlList, int videoWidth, int videoHeight, boolean isAvatarBelowVideo) {
//        List<VideoEffectAvatarAnim> avatarAnimList = GsonUtils.g().fromJson(configJson.optString("avatar"), new TypeToken<List<VideoEffectAvatarAnim>>() {
//        }.getType());
//        if (avatarAnimList != null && avatarUrlList != null) {
//            for (int i = 0, size = avatarAnimList.size(); i < size; i++) {
//                if (avatarUrlList.size() <= i) break;//传的头像数量不够配置文件内容，不再继续生成新动画
//                VideoEffectAvatarAnim avatarAnim = avatarAnimList.get(i);
//                avatarAnim.setUrl(avatarUrlList.get(i));
//                createAvatarAnim(avatarAnim, videoWidth, videoHeight, isAvatarBelowVideo);
//            }
//        }
//    }
//
//    private void parseNameAnim(JSONObject configJson, List<String> nameContentList, int videoWidth, int videoHeight, File file, boolean isAvatarBelowVideo) {
//        List<VideoEffectNameAnim> nameAnimList = GsonUtils.g().fromJson(configJson.optString("text"), new TypeToken<List<VideoEffectNameAnim>>() {
//        }.getType());
//        if (nameAnimList != null && nameContentList != null) {
//            for (int i = 0, size = nameAnimList.size(); i < size; i++) {
//                if (nameContentList.size() <= i) break;//传的头像数量不够配置文件内容，不再继续生成新动画
//                VideoEffectNameAnim nameAnim = nameAnimList.get(i);
//                nameAnim.setText(nameContentList.get(i));
//                createNameAnim(nameAnim, videoWidth, videoHeight, file, isAvatarBelowVideo);
//            }
//        }
//    }
//
//    /**
//     * 读取下载的资源文件中的config.json文件，获取动画的配置信息
//     *
//     * @param configJson
//     * @param file
//     * @param type
//     */

//    private void parseSvgaAndVideoInfoAndPlay(JSONObject configJson, File file, int type, OnStartPlaySvgaAnim onStartPlaySvgaAnim) {
//        try {
//            String fileNameSvga = configJson.getString("svga");
//            File svga = new File(file, fileNameSvga);
//            String fileNameVideo = configJson.getString("video");
//            File mp4 = new File(file, fileNameVideo);
//            if (svga.exists() && mp4.exists()) {
//                Log.i(TAG, type + "即将播放svga路径" + svga.getAbsolutePath());
//                Log.i(TAG, type + "即将播放video路径" + mp4.getAbsolutePath());
//                startVideoAndSvgaEffectAnimation(mp4.getAbsolutePath(), svga.getAbsolutePath(), null, type, onStartPlaySvgaAnim);
//                return;
//            }
//        } catch (JSONException e) {
//            Log.printErrStackTrace(TAG, e);
//        }
//        Log.e(TAG, "svga文件读取失败");
//        onError();
//    }

    /**
     * 读取下载的资源文件中的config.json文件，获取动画的配置信息
     *
     * @param file
     * @param type
     */

    public void parseVideoInfoAndPlay(String videoName, File file, int type) {
        try {
            File mp4 = new File(file, videoName);
            if (mp4.exists()) {
                Log.i(TAG, type + "即将播放文件路径" + mp4.getAbsolutePath());
                startVideoEffectAnimation(mp4.getAbsolutePath(), null, type);
                return;
            }
        } catch (Exception e) {
//            Log.printErrStackTrace(TAG, e);
        }
        Log.e(TAG, "mp4文件读取失败");
        onError();
    }

    /**
     * 开始视频动画
     *
     * @param path
     * @param fd
     * @param type
     */
    public void startVideoEffectAnimation(String path, AssetFileDescriptor fd, int type) {
        if (isStart) {
            Log.w(TAG, "onError 原因是 isStart");
            onError();
            return;
        }
        addTextureView();
        initPlayer(path, fd, type);
        addTextureViewListener();
        isStart = true;
        Log.i(TAG, "开始播放");
    }

    /**
     * 开始视频和SVGA动画
     *
     * @param pathVideo
     * @param pathSvga
     * @param fd
     * @param type
     * @param onStartPlaySvgaAnim
     */
    private void startVideoAndSvgaEffectAnimation(String pathVideo, String pathSvga, AssetFileDescriptor fd, int type, OnStartPlaySvgaAnim onStartPlaySvgaAnim) {
        if (isStart) {
            Log.w(TAG, "onError 原因是 isStart");
            onError();
            return;
        }
        if (onStartPlaySvgaAnims == null) {
            onStartPlaySvgaAnims = new ArrayList<>();
        } else {
            onStartPlaySvgaAnims.clear();
        }
        onStartPlaySvgaAnims.add(onStartPlaySvgaAnim);
        addTextureView();
        initPlayer(pathVideo, fd, type);
        addTextureViewListener(pathSvga);
        isStart = true;
        Log.i(TAG, "开始播放");
    }

    private void addTextureView() {
        mTextureView = null;
        mTextureView = new TextureView(getContext());
        mTextureView.setOpaque(false);
        if (pendingAddCoverView != null) {
            addView(pendingAddCoverView, 0);
        }
        if (indexOfChild(mTextureView) < 0) {
            if (pendingAddCoverView != null) {//有蒙层的话，视频需要放在蒙层之上
                addView(mTextureView, 1, createLayoutParams());
            } else {
                addView(mTextureView, 0, createLayoutParams());
            }
        }
    }

    /**
     * 视频都是16：9的比例
     *
     * @return
     */
    private LayoutParams createLayoutParams() {
        FrameLayout.LayoutParams lp;
        int width = getContext().getResources().getDisplayMetrics().widthPixels;
        int height = width * 16 / 9;
        lp = new LayoutParams(width, height);
        lp.gravity = Gravity.BOTTOM;
        return lp;
    }

    private void initPlayer(final String path, AssetFileDescriptor fd, int type) {
        if (player == null) {
            player = new EffectPlayer(getContext());
        }
        player.setOnVideoCompleteListener(new IEffectPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                onComplete();
            }
        });
        player.setRenderSize(720, 1280);//分辨率，不是视频尺寸大小
        if (!TextUtils.isEmpty(path)) {
            player.setDataSource(path, type);
        } else {
            player.setDataSource(fd, type);
        }
        //根据视频时间回调推进动画进行
        player.setRenderPositionChangedListener(new IEffectPlayer.RenderPositionChangedListener() {
            @Override
            public void renderPositionChanged(final long position) {
                if (animatorList == null) {
                    return;
                }
                for (final ObjectAnimator animator : animatorList) {
                    if (position > animator.getStartDelay() - 100 && position < animator.getDuration() + animator.getStartDelay() + 100) {//有100ms的误差
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                animator.setCurrentPlayTime(position - animator.getStartDelay());
                            }
                        });
                    }
                }
            }
        });
    }

    private void addTextureViewListener() {
        mTextureView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mTextureView.getSurfaceTexture() != null) {
                    mTextureView.getSurfaceTexture().setDefaultBufferSize(720, 1280);
                }
            }
        });
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.i(TAG, "onSurfaceTextureAvailable");
                if (player != null) {
                    surface.setDefaultBufferSize(720, 1280);
                    player.prepare();
                    player.startPlay(new Surface(surface));
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.i(TAG, "onSurfaceTextureDestroyed");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    private void addTextureViewListener(final String pathSvga) {
        mTextureView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mTextureView.getSurfaceTexture() != null) {
                    mTextureView.getSurfaceTexture().setDefaultBufferSize(720, 1280);
                }
            }
        });
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.i(TAG, "onSurfaceTextureAvailable");
                if (player != null) {
                    surface.setDefaultBufferSize(720, 1280);
                    player.prepare();
                    player.startPlay(new Surface(surface));
                }
                if (onStartPlaySvgaAnims != null) {
                    for (OnStartPlaySvgaAnim animator : onStartPlaySvgaAnims) {
                        animator.showSvgaAnim(pathSvga);
                    }
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.i(TAG, "onSurfaceTextureDestroyed");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

//    public void stopAnim() {
//        stopPlayer();
//        GiftVideoEffectResourceHelper.getInstance().cancelAllTask();
//        removeAllViews();
//    }

    private void onComplete() {
        Log.i(TAG, "onComplete");
        if (!isStart) {
            return;
        }
        isStart = false;
        removeAllViews();
        if (onVideoCompleteListener != null) {
            onVideoCompleteListener.onVideoEffectComplete();
        }
    }

    private void onError() {
        Log.e(TAG, "onError");
        removeAllViews();
        isStart = false;
        if (onVideoCompleteListener != null) {
            onVideoCompleteListener.onError();
        }
    }

    private void stopPlayer() {
        if (isStart) {
            if (player != null) {
                player.stopPlay();
            }
            isStart = false;
        }
    }

    private String getTaskTag() {
        return String.valueOf(this.hashCode());
    }

//    // 动画相关
//    private void createAvatarAnim(VideoEffectAvatarAnim avatarAnim, int videoWidth, int videoHeight, boolean isAvatarBelowVideo) {
//        List<EffectAnim> animList = avatarAnim.getAnim();
//        if (animList == null) return;
//
//        int width = (int) (videoWidth * avatarAnim.getWidth());
//        int height = (int) (videoHeight * avatarAnim.getHeight());
//
//        CircleImageView imageView = new CircleImageView(getContext());
////        ImageLoaderX.loadWithReset(avatarAnim.getUrl()).type(ImageType.IMAGE_TYPE_URL).cornerRadius(width).into(imageView);
//        Glide.with(RoomKit.getInstance().getContext()).load(avatarAnim.getUrl()).into(imageView);
//
//        imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
//        imageView.setAlpha(0f);
//        if (avatarAnim.getBorder() != 0) {
//            imageView.setBorderShader(ColorUtils.parseAllColor(avatarAnim.getStartcolor(), Color.TRANSPARENT), ColorUtils.parseAllColor(avatarAnim.getEndcolor(), Color.TRANSPARENT));
//            imageView.setBorderWidth((int) (videoWidth * avatarAnim.getBorder()));
//        }
//        insertAnim(animList, imageView, width, height, videoWidth, videoHeight, isAvatarBelowVideo);
//    }

//    private void createNameAnim(VideoEffectNameAnim nameAnim, int videoWidth, int videoHeight, File file, boolean isAvatarBelowVideo) {
//        List<EffectAnim> animList = nameAnim.getAnim();
//        if (animList == null) return;
//        HandyTextView textView = new HandyTextView(getContext());
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, nameAnim.getFontSize());
//        textView.setTextColor(ColorUtils.parseAllColor(nameAnim.getTextColor(), Color.TRANSPARENT));
//        textView.setPadding(UIUtils.getPixels(4), 0, UIUtils.getPixels(4), 0);
//        textView.setSingleLine();
//        if (nameAnim.getMaxLen() != 0) {
//            textView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(nameAnim.getMaxLen())});
//            textView.setEllipsize(TextUtils.TruncateAt.END);
//        }
//        textView.setText(nameAnim.getText());
//        textView.setAlpha(0f);
//        textView.setGravity(Gravity.CENTER);
//        int width = (int) (videoWidth * nameAnim.getWidth());
//        int height = (int) (videoHeight * nameAnim.getHeight());
//        textView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
//        if (StringUtils.isEmpty(nameAnim.getStartcolor())) {
//            if (StringUtils.isNotBlank(nameAnim.getBackground())) {
//                addTextViewBackgroundByFile(new File(file, nameAnim.getBackground()), textView);
//
//            }
//        } else {
//            addTextViewBackgroundByJson(nameAnim, textView, videoWidth);
//        }
//        insertAnim(animList, textView, width, height, videoWidth, videoHeight, isAvatarBelowVideo);
//    }

    /**
     * 根据zip里的png文件设置名牌背景
     *
     * @param bgfile
     * @param textView
     */
//    private void addTextViewBackgroundByFile(File bgfile, TextView textView) {
//        if (bgfile.exists()) {
//            Bitmap bitmap = ImageUtil.decodeFile(bgfile.getAbsolutePath());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                textView.setBackground(new BitmapDrawable(UIUtils.getResources(), bitmap));
//            } else {
//                textView.setBackgroundDrawable(new BitmapDrawable(UIUtils.getResources(), bitmap));
//            }
//        }
//    }

//    /**
//     * 根据json里的配置信息设置名牌背景
//     *
//     * @param nameAnim
//     * @param textView
//     */
//    private void addTextViewBackgroundByJson(VideoEffectNameAnim nameAnim, TextView textView, int videoWidth) {
//        int colors[] = {ColorUtils.parseAllColor(nameAnim.getStartcolor(), Color.TRANSPARENT), ColorUtils.parseAllColor(nameAnim.getEndcolor(), Color.TRANSPARENT)};
//        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
//        drawable.setCornerRadius(videoWidth * nameAnim.getCorner());
//        textView.setBackground(drawable);
//    }

//    private void insertAnim(List<EffectAnim> animList, View targetView, int selfWidth, int selfHeight, int videoWidth, int videoHeight, boolean isAvatarBelowVideo) {
//        if (isAvatarBelowVideo) {
//            addView(targetView, pendingAddCoverView != null ? 1 : 0);
//        } else {
//            addView(targetView);
//        }
//        for (int i = 0, size = animList.size(); i < size - 1; i++) {//两个anim可以生成一个ObjectAnimator
//            EffectAnim nowAnim = animList.get(i);
//            EffectAnim nextAnim = animList.get(i + 1);
//            PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat(TRANSLATION_X, nowAnim.getX() * videoWidth - selfWidth / 2, nextAnim.getX() * videoWidth - selfWidth / 2);
//            PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat(TRANSLATION_Y, getTopDistance(nowAnim.getY(), selfHeight, videoHeight), getTopDistance(nextAnim.getY(), selfHeight, videoHeight));
//            PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat(SCALE_X, nowAnim.getScale(), nextAnim.getScale());
//            PropertyValuesHolder pvh4 = PropertyValuesHolder.ofFloat(SCALE_Y, nowAnim.getScale(), nextAnim.getScale());
//            PropertyValuesHolder pvh5 = PropertyValuesHolder.ofFloat(ALPHA, nowAnim.getAlpha(), nextAnim.getAlpha());
//            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView, pvh1, pvh2, pvh3, pvh4, pvh5).setDuration(nextAnim.getTime() - nowAnim.getTime());
//            objectAnimator.setStartDelay(nowAnim.getTime());
//            targetView.setPivotX(selfWidth / 2);//以中心为标准点
//            targetView.setPivotY(selfHeight / 2);
//            if (nowAnim.getSpring() != null) {
//                objectAnimator.setInterpolator(new DesignBounceInterpolator(10, 40, 60));
//            } else if (!StringUtils.isEmpty(nowAnim.getInterpolator())) {
//                addPathInterpolator(nowAnim, objectAnimator);
//            }
//            animatorList.add(objectAnimator);
//        }
//    }

    /**
     * 因为视频高度是根据宽度*16/9,再沉底摆放，所以纵向坐标也要用 （1-底部）的方式去计算，才能得到正确距离
     *
     * @param fraction
     * @param selfHeight
     * @param videoHeight
     * @return
     */
//    private float getTopDistance(float fraction, int selfHeight, int videoHeight) {
//        float bottomDistance = (1 - fraction) * videoHeight + selfHeight / 2;
//        return UIUtils.getScreenHeight() - bottomDistance - topDistance - UIUtils.getPixels(30);
//    }

    private int topDistance;

    /**
     * 群聊中cp礼物设置全屏，减掉toolbar和状态栏的高度
     *
     * @param topDistance
     */
    public void setTopDistance(int topDistance) {
        this.topDistance = topDistance;
    }

//    private void addPathInterpolator(EffectAnim nowAnim, ObjectAnimator objectAnimator) {//贝塞尔曲线插值器需求API>=21
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            String[] array = nowAnim.getInterpolator().split(",");
//            List<Float> floatList = new ArrayList<>();
//            for (String s : array) {
//                floatList.add(Float.valueOf(s));
//            }
//            if (floatList.size() == 4) {
//                PathInterpolator pathInterpolator = new PathInterpolator(floatList.get(0), floatList.get(1), floatList.get(2), floatList.get(3));
//                objectAnimator.setInterpolator(pathInterpolator);
//            }
//        }
//    }

    private List<String> getStringList(String s) {
        if (StringUtils.isEmpty(s)) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(s);
        }
    }

    public interface OnVideoCompleteListener {
        void onVideoEffectComplete();

        void onError();
    }

    public interface OnStartPlaySvgaAnim {
        void showSvgaAnim(String svgaPath);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        clearDownloadReference();
    }

//    private void clearDownloadReference() {
//        for (String resourceId : downloadingEffect) {
//            GiftVideoEffectResourceHelper.getInstance().removePrepareListenerFromMap(resourceId);
//        }
//    }



}
