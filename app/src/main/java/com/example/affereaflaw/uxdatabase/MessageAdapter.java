package com.example.affereaflaw.uxdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by Affe Reaflaw on 10/23/2017.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{


    final private List<MessagesGetSet> MessageList;
    final private String authUserMessage = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public MessageAdapter(List<MessagesGetSet> MessageList) {

        this.MessageList = MessageList;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row ,parent, false);

        return new MessageViewHolder(v);

    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public LinearLayout parentView;

        public MessageViewHolder(View view) {
            super(view);
            messageText = (TextView) view.findViewById(R.id.messageView);
            parentView = (LinearLayout) view.findViewById(R.id.message_parent);

        }
    }


    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int i) {
        MessagesGetSet c = MessageList.get(i);
        String fromUser = c.getFrom();

        if (!authUserMessage.equals(fromUser)){
            viewHolder.messageText.setBackgroundResource(R.drawable.chat_background1);
        }
        else {
            viewHolder.messageText.setBackgroundResource(R.drawable.chat_background);
            //viewHolder.parentView.setHorizontalGravity(Gravity.END);
        }
        viewHolder.messageText.setText(c.getPesan());
    }

    @Override
    public int getItemCount() {
        return MessageList.size();
    }

}
