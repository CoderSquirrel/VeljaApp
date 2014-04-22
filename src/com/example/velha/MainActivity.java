package com.example.velha;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements OnClickListener {
	Jogador[][] tabuleiro = new Jogador[3][3];
	ImageView[][] TAB = new ImageView[3][3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TAB[0][0] = (ImageView) findViewById(R.id.iv00);
		TAB[0][1] = (ImageView) findViewById(R.id.iv01);
		TAB[0][2] = (ImageView) findViewById(R.id.iv02);
		TAB[1][0] = (ImageView) findViewById(R.id.iv10);
		TAB[1][1] = (ImageView) findViewById(R.id.iv11);
		TAB[1][2] = (ImageView) findViewById(R.id.iv12);
		TAB[2][0] = (ImageView) findViewById(R.id.iv20);
		TAB[2][1] = (ImageView) findViewById(R.id.iv21);
		TAB[2][2] = (ImageView) findViewById(R.id.iv22);
		setListiner();

		findViewById(R.id.iv_novo).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	private void Decisao() {
		if (Jogo.vencedor(tabuleiro, Jogador.COMPUTADOR)) {
		} else if (Jogo.vencedor(tabuleiro, Jogador.PESSOA)) {
		} else if (verificarTabuleiroCheio(tabuleiro)) {

		} else {
			Jogador[][] tab = tabuleiro.clone();

			int[][] custos = new int[3][3];

			for (int l = 0; l < 3; l++) {

				for (int c = 0; c < 3; c++) {

					if (tab[l][c] == null) {

						tab[l][c] = Jogador.COMPUTADOR;

						custos[l][c] = Jogo.min(tab);

						tab[l][c] = null;

					} else {

						custos[l][c] = -99;

					}

				}

			}

			int l = 0, c = 0;

			int maior = Integer.MIN_VALUE;

			for (int i = 0; i < custos.length; i++) {
				for (int j = 0; j < custos.length; j++) {
					if (custos[i][j] != -99) {
						if (maior < custos[i][j]) {
							maior = custos[i][j];

							l = i;

							c = j;

						}

					}

				}

			}
			Log.d("Posição", "L= " + l + "\tC= " + c);

			tabuleiro[l][c] = Jogador.COMPUTADOR;
			TAB[l][c].setImageResource(R.drawable.o);
			TAB[l][c].setEnabled(false);

			imprimir(custos);
		}
	}

	public boolean verificarTabuleiroCheio(Jogador[][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if (tab[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public void jogadaComputador() {
		Decisao();

	}

	public void setListiner() {
		for (int i = 0; i < TAB.length; i++) {
			for (int j = 0; j < TAB.length; j++) {

				TAB[i][j].setOnClickListener(tab);
			}
		}
	}

	public void setListinerNull() {
		for (int i = 0; i < TAB.length; i++) {
			for (int j = 0; j < TAB.length; j++) {

				TAB[i][j].setOnClickListener(null);
			}
		}
	}

	OnClickListener tab = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("clicado", v.getId() + "");
			ImageView img = null;
			switch (v.getId()) {
			case R.id.iv00:
				tabuleiro[0][0] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv00);
				break;
			case R.id.iv01:
				tabuleiro[0][1] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv01);
				break;
			case R.id.iv02:
				tabuleiro[0][2] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv02);
				break;
			case R.id.iv10:
				tabuleiro[1][0] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv10);
				break;
			case R.id.iv11:
				tabuleiro[1][1] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv11);
				break;
			case R.id.iv12:
				tabuleiro[1][2] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv12);
				break;
			case R.id.iv20:
				tabuleiro[2][0] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv20);
				break;
			case R.id.iv21:
				tabuleiro[2][1] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv21);

				break;
			case R.id.iv22:
				tabuleiro[2][2] = Jogador.PESSOA;
				img = (ImageView) findViewById(R.id.iv22);

				break;

			default:
				break;
			}
			if (img != null) {
				img.setImageResource(R.drawable.x);
				img.setEnabled(false);
			}
			verificar();
			jogadaComputador();
			verificar();
		}

		public void verificar() {
			if (Jogo.vencedor(tabuleiro, Jogador.PESSOA)) {
				Toast.makeText(MainActivity.this, "Pessoa Ganhou",
						Toast.LENGTH_SHORT).show();
				setListinerNull();

			} else if (Jogo.vencedor(tabuleiro, Jogador.COMPUTADOR)) {
				Toast.makeText(MainActivity.this, "Computador Ganhou",
						Toast.LENGTH_SHORT).show();
				setListinerNull();
			}
		}
	};

	public void imprimir(int custos[][]) {
		for (int i = 0; i < tabuleiro.length; i++) {
			Log.e("", tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | "
					+ tabuleiro[i][2] + " | ");
		}

		for (int i = 0; i < custos.length; i++) {
			Log.e("", custos[i][0] + " | " + custos[i][1] + " | "
					+ custos[i][2] + " | ");
		}
	}

	public void novoJogo() {
		tabuleiro = new Jogador[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				TAB[i][j].setImageResource(R.drawable.vazio);
				TAB[i][j].setEnabled(true);
			}
		}
		setListiner();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.iv_novo) {
			novoJogo();
		}

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.acao_sobre:
			startActivity(new Intent(this, Sobre.class));
			break;
		case R.id.acao_novo:
			novoJogo();
			break;
		default:
			break;
		}
		return false;

	}
}
