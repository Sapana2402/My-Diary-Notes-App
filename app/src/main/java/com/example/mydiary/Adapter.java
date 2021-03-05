package com.example.mydiary;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Adapter extends FirebaseRecyclerAdapter<User,Adapter.myviewholder> {
    private Context context;
    public Adapter(@NonNull FirebaseRecyclerOptions<User> options, Context context) {
        super(options);
        this.context=context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User model) {
        holder.subjectt.setText(model.getSubjectt());
        holder.detilst.setText(model.getDetilst());

holder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.scale_annimation));

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.dailog_layout))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout)dialog.getHolderView();
                EditText subjectt = holderView.findViewById(R.id.update_subject);
                EditText detilst = holderView.findViewById(R.id.update_details);
                Button update=holderView.findViewById(R.id.update);

                subjectt.setText(model.getSubjectt());
                detilst.setText(model.getDetilst());


                update.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("subjectt",subjectt.getText().toString());
                        map.put("detilst",detilst.getText().toString());

                        FirebaseDatabase.getInstance().getReference()
                                .child("hii")
                                .child(getRef(position).getKey())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

                dialog.show();
            }
        });





       holder.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseDatabase.getInstance().getReference().child("hii").child(getRef(position).getKey())
                      .removeValue()
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {

                           }
                       });
           }
       });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(v.getContext(),onclickdisplay.class);
                intent1.putExtra("subjectt",model.getSubjectt());
                intent1.putExtra("detilst",model.getDetilst());
                Pair[] pairs = new Pair[2];
                pairs[0]=new Pair<View, String>(holder.subjectt,"imagetransition");
                pairs[1]=new Pair<View, String>(holder.detilst,"texttransition");


                ActivityOptions aoptions= ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(),pairs);
              v.getContext().startActivity(intent1, aoptions.toBundle());

                //without animation
              //  Intent intentl = new Intent (v.getContext(),onclickdisplay.class);

               //intentl.putExtra("subjectt",model.getSubjectt());
                //intentl.putExtra("detilst",model.getDetilst());
                //v.getContext().startActivity(intentl);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView subjectt,detilst;
ImageView delete,update;
LinearLayout container;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            subjectt=itemView.findViewById(R.id.ms_subject);
            detilst=itemView.findViewById(R.id.ms_details);
delete=itemView.findViewById(R.id.delete);
update=itemView.findViewById(R.id.update);
container=itemView.findViewById(R.id.container);
        }
    }
}
