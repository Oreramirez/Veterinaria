package com.upt.moviles.veterinaria.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import android.widget.Toast;


import com.upt.moviles.veterinaria.R;
import com.upt.moviles.veterinaria.adapter.DosenAdapter;
import com.upt.moviles.veterinaria.model.ResponseDosen;
import com.upt.moviles.veterinaria.model.SemuadosenItem;
import com.upt.moviles.veterinaria.util.api.BaseApiService;
import com.upt.moviles.veterinaria.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeterinariaActivity extends AppCompatActivity {

    @BindView(R.id.rvDosen)
    RecyclerView rvDosen;
    ProgressDialog cargando;

    Context mContext;
    List<SemuadosenItem> semuadosenItemList = new ArrayList<>();
    DosenAdapter dosenAdapter;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaria);

        getSupportActionBar().setTitle("Veterinarias");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        dosenAdapter = new DosenAdapter(this, semuadosenItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDosen.setLayoutManager(mLayoutManager);
        rvDosen.setItemAnimator(new DefaultItemAnimator());

        getResultListDosen();
    }

    private void getResultListDosen(){
        cargando = ProgressDialog.show(this, null, "Por favor espere...", true, false);

        mApiService.getSemuaDosen().enqueue(new Callback<ResponseDosen>() {
            @Override
            public void onResponse(Call<ResponseDosen> call, Response<ResponseDosen> response) {
                if (response.isSuccessful()){
                    cargando.dismiss();

                    final List<SemuadosenItem> semuaDosenItems = response.body().getSemuadosen();

                    rvDosen.setAdapter(new DosenAdapter(mContext, semuaDosenItems));
                    dosenAdapter.notifyDataSetChanged();
                } else {
                    cargando.dismiss();
                    Toast.makeText(mContext, "Error al recuperar los datos de la veterinaria", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDosen> call, Throwable t) {
                cargando.dismiss();
                Toast.makeText(mContext, "Error de Conexión a Internet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
