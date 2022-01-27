package com.yandex.together.ui.my_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yandex.together.R
import com.yandex.together.ui.EventCardVO

class MyEventsViewModel() : ViewModel() {

    private val _eventsLiveData = MutableLiveData<List<EventCardVO>>()
    val eventsLiveData: LiveData<List<EventCardVO>> = _eventsLiveData

    fun getEvents() {
        _eventsLiveData.postValue(
            listOf(
                EventCardVO(
                    R.drawable.background_sport_icon,
                    "Спорт",
                    "Играем в футбол",
                    "Собираемся на игру в футбол 11 на 11 на каком-то стадионе. Все, кто хочет, присоединяйтесь.",
                    18,
                    22,
                    "27.02.2022"
                ),
                EventCardVO(
                    R.drawable.backgroud_table_games_icon,
                    "Настольные игры",
                    "Покер (Техаский холдем)",
                    "Покер (англ. poker) — карточная игра, цель которой собрать выигрышную комбинацию или вынудить всех соперников прекратить участвовать в игре. Игра идёт с полностью или частично закрытыми картами",
                    6,
                    10,
                    "27.02.2022"
                ),
                EventCardVO(
                    R.drawable.background_nature_icon,
                    "Природа",
                    "Рыбалка на карпа",
                    "Собираемся на игру в футбол 11 на 11 на каком-то стадионе. Все, кто хочет, присоединяйтесь.",
                    18,
                    22,
                    "01.03.2022"
                ),
                EventCardVO(
                    R.drawable.background_sport_icon,
                    "Спорт",
                    "Играем в футбол",
                    "Собираемся на игру в футбол 11 на 11 на каком-то стадионе. Все, кто хочет, присоединяйтесь.",
                    18,
                    22,
                    "27.02.2022"
                ),
                EventCardVO(
                    R.drawable.backgroud_table_games_icon,
                    "Настольные игры",
                    "Покер (Техаский холдем)",
                    "Покер (англ. poker) — карточная игра, цель которой собрать выигрышную комбинацию или вынудить всех соперников прекратить участвовать в игре. Игра идёт с полностью или частично закрытыми картами",
                    6,
                    10,
                    "27.02.2022"
                ),
                EventCardVO(
                    R.drawable.background_nature_icon,
                    "Природа",
                    "Рыбалка на карпа",
                    "Собираемся на игру в футбол 11 на 11 на каком-то стадионе. Все, кто хочет, присоединяйтесь.",
                    18,
                    22,
                    "01.03.2022"
                ),
            )
        )
    }
}