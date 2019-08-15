package vao.wallet;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TokenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);

        ImageButton tokenCardMain = view.findViewById(R.id.tokenCardMain);

        if (getArguments() != null) {
            Bundle args = getArguments();

            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(args.getInt("imgRes"))).getBitmap();
            Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * (28.0 / bitmap.getHeight())), 28, true));
            tokenCardMain.setImageDrawable(drawable);
            tokenCardMain.setScaleType(ImageButton.ScaleType.CENTER);
        }

        return view;
    }
}
