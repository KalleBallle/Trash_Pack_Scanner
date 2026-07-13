package com.example.packscan.Data

import androidx.room.Database
import androidx.room.RoomDatabase


import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader


@Database(entities = [Figure::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun figureDao(): FigureDao
}

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "packscan_database"
            )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val figuresList = loadFiguresFromJson(context)
                            getDatabase(context).figureDao().insertManyFigures(figuresList)
                        }
                    }
                })

                .build()
            INSTANCE = instance
            instance
        }
    }
}

//stoppa in all hät sen
fun getAllKnownFigures():  List<Figure> = listOf(

Figure("TP-1", "Grot Dog", "Red", "Trashpack series 1", "Common", "THE GRUBZ", 1, true),
Figure("TP-2", "Smelly Fish", "Purple", "Trashpack series 1", "Common", "THE GRUBZ", 1000, true),

    Figure("TP-3", "Bud", "Purple", "Trashpack series 1", "Common", "BIN-SECTS"),
    Figure("TP-4", "Super", "Gold", "Trashpack series 1", "Limited Edition", "LIMITED EDITION"),


)
fun loadFiguresFromJson(context: Context): List<Figure> {
    return try {

        val inputStream = context.assets.open("trash_pack_data.json")
        val reader = InputStreamReader(inputStream)


        val itemType = object : TypeToken<List<Figure>>() {}.type
        val figures: List<Figure> = Gson().fromJson(reader, itemType)

        reader.close()
        figures
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}