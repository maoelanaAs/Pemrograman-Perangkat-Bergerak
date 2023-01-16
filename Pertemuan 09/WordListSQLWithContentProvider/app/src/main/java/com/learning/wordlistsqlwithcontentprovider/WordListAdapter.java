package com.learning.wordlistsqlwithcontentprovider;

import static com.learning.wordlistsqlwithcontentprovider.Contract.CONTENT_PATH;
import static com.learning.wordlistsqlwithcontentprovider.Contract.CONTENT_URI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private static final String TAG = WordListAdapter.class.getSimpleName();

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_WORD = "WORD";
    public static final String EXTRA_POSITION = "POSITION";


    private String queryUri = CONTENT_URI.toString();
    private static final String[] projection = new String[] {CONTENT_PATH};
    private String selectionClause = null;
    private String selectionArgs[] = null;
    private String sortOrder = "ASC";

    private final LayoutInflater mInflater;
    Context mContext;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        final WordViewHolder h = holder;

        String word = "";
        int id = -1;

        Cursor cursor = mContext.getContentResolver().query(Uri.
                parse(queryUri), null, null, null, sortOrder);
        if (cursor != null) {
            if (cursor.moveToPosition(position)) {
                int indexWord = cursor.getColumnIndex(Contract.WordList.KEY_WORD);
                word = cursor.getString(indexWord);
                holder.wordItemView.setText(word);
                int indexId = cursor.getColumnIndex(Contract.WordList.KEY_ID);
                id = cursor.getInt(indexId);
            } else {
                holder.wordItemView.setText(R.string.error_no_word);
            }
            cursor.close();
        } else {
            Log.e (TAG, "onBindViewHolder: Cursor is null.");
        }

        holder.delete_button.setOnClickListener(new MyButtonOnClickListener(id, word) {

            @Override
            public void onClick(View v) {
                selectionArgs = new String[]{Integer.toString(id)};
                int deleted = mContext.getContentResolver().delete(CONTENT_URI, CONTENT_PATH,
                        selectionArgs);
                if (deleted > 0) {
                    // Need both calls
                    notifyItemRemoved(h.getAdapterPosition());
                    notifyItemRangeChanged(h.getAdapterPosition(), getItemCount());
                } else {
                    Log.d (TAG, mContext.getString(R.string.not_deleted) + deleted);
                }
            }
        });

        holder.edit_button.setOnClickListener(new MyButtonOnClickListener(id, word) {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditWordActivity.class);

                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_POSITION, h.getAdapterPosition());
                intent.putExtra(EXTRA_WORD, word);

                ((Activity) mContext).startActivityForResult(intent, MainActivity.WORD_EDIT);
            }
        });
    }

    @Override
    public int getItemCount() {
        Cursor cursor =
                mContext.getContentResolver().query(
                        Contract.ROW_COUNT_URI, new String[] {"count(*) AS count"},
                        selectionClause, selectionArgs, sortOrder);
        try {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        }
        catch (Exception e){
            Log.d(TAG, "EXCEPTION getItemCount: " + e);
            return -1;
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        Button delete_button;
        Button edit_button;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.word);
            delete_button = itemView.findViewById(R.id.delete_button);
            edit_button = itemView.findViewById(R.id.edit_button);
        }
    }
}

