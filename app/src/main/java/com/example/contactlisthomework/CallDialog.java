package com.example.contactlisthomework;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CallDialog extends DialogFragment {

    private String calling_to;

    private OnCallDialogInteractionListener mListener;

    static CallDialog newInstance(String calling_to){
        return new CallDialog(calling_to);
    }

    public CallDialog(String calling_to) {
        this.calling_to = calling_to;
    }

    public interface OnCallDialogInteractionListener{
        void onCallDialogPositiveClick(DialogFragment dialog);
        void onCallDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.call_question) + calling_to);
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogPositiveClick(CallDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogNegativeClick(CallDialog.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallDialogInteractionListener) {
            mListener = (OnCallDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCallDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}