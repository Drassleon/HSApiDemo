package pe.edu.upc.consumingapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.consumingapi.configuration.RetrofitConfig
import pe.edu.upc.consumingapi.models.Card
import pe.edu.upc.consumingapi.repository.CardRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Activity
import android.graphics.Typeface
import android.view.inputmethod.InputMethodManager





class MainActivity : AppCompatActivity() {

    private val cardRepository = RetrofitConfig().getRetrofitInstance()?.create(CardRepository::class.java)

    private lateinit var cardFrontImageView : ImageView
    private lateinit var cardSearchView: SearchView
    private lateinit var titleCardTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardFrontImageView = findViewById(R.id.cardImageView)
        cardSearchView = findViewById(R.id.cardNameSearchBar)
        titleCardTextView = findViewById(R.id.cardNameTextView)

        val searchInnerText : TextView = findViewById(androidx.appcompat.R.id.search_src_text)
        searchInnerText.typeface = Typeface.SANS_SERIF
        searchBarTextListener()
    }
    fun loadCardImage(cardImageUrl : String){
        Picasso.with(this@MainActivity)
            .load(cardImageUrl)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_aspect_ratio_black_48dp)
            .resize(300,450)
            .into(cardFrontImageView)
    }
    private fun searchBarTextListener(){
        cardSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!=null)
                Log.d("SearchBar","New Text on Search Bar: ${newText}")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null)
                {
                    val imm = this@MainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    var view = this@MainActivity.currentFocus
                    imm.hideSoftInputFromWindow(view?.windowToken,0)
                    getCardService(query)
                }
                else
                {
                    Toast.makeText(this@MainActivity,"Card Name cannot be empty",Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })
    }
    fun getCardService(cardName : String){
        cardRepository?.getCardByName(cardName)?.enqueue(object: Callback<List<Card>>{
            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                Log.d("Networking","Could not obtain card data",t)
                Toast.makeText(this@MainActivity,"Could not obtain card data",Toast.LENGTH_SHORT).show()
                return
            }

            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                Log.d("Networking","Connected to HS card service")
                if(response.code()==401)
                {
                    Log.d("Networking","Unauthorized")
                    Log.d("Networking",call.request().headers().toString())
                }
                else if(response.body() == null)
                {
                    Log.d("Networking","Body is null")
                    Log.d("Networking",response.raw().toString())
                }
                else{
                    Log.d("Networking","Card Data obtained")
                    val cardList: List<Card> = response.body() as List<Card>
                    Log.d("Networking","Image URL: ${cardList[0].img}")
                    loadCardImage(cardList[0].img)
                    titleCardTextView.text = cardList[0].flavor
                }
                return
            }
        })
    }
}
