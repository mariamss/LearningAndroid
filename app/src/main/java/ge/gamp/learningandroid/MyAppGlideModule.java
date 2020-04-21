package ge.gamp.learningandroid;

import com.bumptech.glide.annotation.GlideModule;

import java.lang.annotation.Annotation;

public class MyAppGlideModule implements GlideModule {

    @Override
    public String glideName() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
