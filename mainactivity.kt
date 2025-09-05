package com.example.superheroapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FichaHeroiActivity : AppCompatActivity() {

    private lateinit var codenameTextView: TextView
    private lateinit var alignmentTextView: TextView
    private lateinit var powersTextView: TextView
    private lateinit var avatarImageView: ImageView
    private lateinit var fichaHeroiLayout: LinearLayout
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_heroi)

        // Inicializa as referências dos componentes da UI
        codenameTextView = findViewById(R.id.codenameTextView)
        alignmentTextView = findViewById(R.id.alignmentTextView)
        powersTextView = findViewById(R.id.powersTextView)
        avatarImageView = findViewById(R.id.avatarImageView)
        fichaHeroiLayout = findViewById(R.id.fichaHeroiLayout)
        editButton = findViewById(R.id.editButton)

        // Extrai os dados enviados da Activity anterior
        val heroCodename = intent.getStringExtra("HERO_CODENAME")
        val heroAlignment = intent.getStringExtra("HERO_ALIGNMENT")
        val heroPowers = intent.getStringArrayListExtra("HERO_POWERS")
        val heroAvatarResId = intent.getIntExtra("HERO_AVATAR_RES_ID", R.drawable.ic_launcher_background)

        // Define os textos e a imagem na tela
        codenameTextView.text = "Codinome: $heroCodename"
        alignmentTextView.text = "Alinhamento: $heroAlignment"
        powersTextView.text = "Poderes:\n- ${heroPowers?.joinToString("\n- ")}"
        avatarImageView.setImageResource(heroAvatarResId)
        
        // Desafio 2: Mudar a cor de fundo de acordo com o alinhamento
        val backgroundColor = when (heroAlignment) {
            "Herói" -> Color.parseColor("#42A5F5") // Um azul mais vibrante para heróis
            "Vilão" -> Color.parseColor("#E53935") // Um vermelho mais forte para vilões
            "Anti-herói" -> Color.parseColor("#616161") // Um cinza mais escuro para anti-heróis
            else -> Color.WHITE
        }
        fichaHeroiLayout.setBackgroundColor(backgroundColor)
        
        // Desafio 1: Botão "Editar" para voltar à tela anterior
        editButton.setOnClickListener {
            // Cria um Intent para voltar para a tela de criação do herói
            val intent = Intent(this, CriacaoHeroiActivity::class.java).apply {
                // Passa os dados de volta para que a tela possa ser preenchida
                putExtra("HERO_CODENAME", heroCodename)
                putExtra("HERO_ALIGNMENT", heroAlignment)
                putStringArrayListExtra("HERO_POWERS", heroPowers)
                putExtra("HERO_AVATAR_RES_ID", heroAvatarResId)
            }
            startActivity(intent)
            finish() // Fecha a tela final para que o usuário não possa voltar
        }
    }
}