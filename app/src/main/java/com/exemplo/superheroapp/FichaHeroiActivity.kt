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

        codenameTextView = findViewById(R.id.codenameTextView)
        alignmentTextView = findViewById(R.id.alignmentTextView)
        powersTextView = findViewById(R.id.powersTextView)
        avatarImageView = findViewById(R.id.avatarImageView)
        fichaHeroiLayout = findViewById(R.id.fichaHeroiLayout)
        editButton = findViewById(R.id.editButton)

        val heroCodename = intent.getStringExtra("HERO_CODENAME")
        val heroAlignment = intent.getStringExtra("HERO_ALIGNMENT")
        val heroPowers = intent.getStringArrayListExtra("HERO_POWERS")
        val heroAvatarResId = intent.getIntExtra("HERO_AVATAR_RES_ID", R.drawable.ic_launcher_background)

        codenameTextView.text = "Codinome: $heroCodename"
        alignmentTextView.text = "Alinhamento: $heroAlignment"
        powersTextView.text = "Poderes:\n- ${heroPowers?.joinToString("\n- ")}"
        avatarImageView.setImageResource(heroAvatarResId)
        
        val backgroundColor = when (heroAlignment) {
            "Herói" -> Color.parseColor("#42A5F5") 
            "Vilão" -> Color.parseColor("#E53935") 
            "Anti-herói" -> Color.parseColor("#616161") 
            else -> Color.WHITE
        }
        fichaHeroiLayout.setBackgroundColor(backgroundColor)
        
        editButton.setOnClickListener {

            val intent = Intent(this, CriacaoHeroiActivity::class.java).apply {

                putExtra("HERO_CODENAME", heroCodename)
                putExtra("HERO_ALIGNMENT", heroAlignment)
                putStringArrayListExtra("HERO_POWERS", heroPowers)
                putExtra("HERO_AVATAR_RES_ID", heroAvatarResId)
            }
            startActivity(intent)
            finish() 
        }
    }
}
