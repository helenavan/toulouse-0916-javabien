package fr.wildcodeschool.apprenti.javabien;

/**
 * Created by tuffery on 22/09/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;


public class CustomGridAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Contenant> mobileValues;
    private ArrayList<String> listenom = new ArrayList<String>();

    //constructeur
    public CustomGridAdapter(Context context, ArrayList<Contenant> mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        // boucle pour récupérer les noms des exos(placés dans listenom)
        for(int i=0;i<mobileValues.size();i++) {


            listenom.add(mobileValues.get(i).getExonom());
        }
        // merdier qui se charge d'ajouter des boutons en fonction de la longueur de la liste

        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {







            //attribution de l'image d'activation du boutton
            if(mobileValues.get(position).getAvancement()==1){
                convertView = inflater.inflate(R.layout.row_grid, parent,false);
            }else{
                convertView = inflater.inflate(R.layout.row_grid_locked, parent,false);
            }

            // les boutons sont vraiment créés
            holder = new ViewHolder();
            holder.btn1 =(Button)convertView.findViewById(R.id.imgProfilePicture);

            // si l'avancement est !1  alors le bouton n'est pas clickable
            if(mobileValues.get(position).getAvancement()==1){


            holder.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((GridView)parent).performItemClick(v,position,0);

                }
            });
            }
            // set Text into button



            Button textView = (Button) convertView
                    .findViewById(R.id.imgProfilePicture);
            textView.setText(listenom.get(position));



        } else {
            holder = (ViewHolder) convertView.getTag(); // j'y comprend rien à ça
        }
return convertView; // renvoie la vue je suppose
    }

    // méthode indispensable pour créer des boutons viables
    static class ViewHolder{
        private Button btn1;

    }

    // des trucs utiles pour que la classe soit validée mais qui ne servent à rien sinon
    @Override
    public int getCount() {
        return mobileValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}