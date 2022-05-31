package com.example.todo.airbnb.data.repository

import com.example.todo.airbnb.data.Accommodations
import com.example.todo.airbnb.data.Travel
import com.example.todo.airbnb.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl : MainRepository {

    private val dummyImage =
        "https://a0.muscache.com/im/pictures/2f13349d-879d-43c6-83e3-8e5679291d53.jpg?im_w=480"

    private val locations = listOf(
        Travel(dummyImage, "양재동, 서초구, 서울특별시"),
        Travel(dummyImage, "양재역 사거리, 양재1동"),
        Travel(dummyImage, "서울특별시 양재2동 시민의숲앞"),
        Travel(dummyImage, "서울특별시 양재2동 양재 IC"),
        Travel(dummyImage, "강릉시, 강원도"),
        Travel(dummyImage, "대전광역시 서구"),
        Travel(dummyImage, "대전광역시 중구"),
        Travel(dummyImage, "대전광역시 동구"),
        Travel(dummyImage, "경기도 수원시")
    )

    private val travelList = listOf(
        Travel(dummyImage, "서울", "차로 30분 거리"),
        Travel(dummyImage, "광주", "차로 4시간 거리"),
        Travel(dummyImage, "의정부", "차로 30분 거리"),
        Travel(dummyImage, "수원", "차로 45분 거리"),
        Travel(dummyImage, "대구", "차로 3.5시간 거리"),
        Travel(dummyImage, "울산", "차로 4.5시간 거리"),
        Travel(dummyImage, "대전", "차로 2시간 거리"),
        Travel(dummyImage, "부천", "차로 30분 거리"),
    )

    private val accommodationList = listOf(
        Accommodations(dummyImage, "자연생활을 만끽할 수\n있는 숙소"),
        Accommodations(dummyImage, "독특한 공간"),
        Accommodations(dummyImage, "자연생활을 만끽할 수\n있는 숙소"),
        Accommodations(dummyImage, "독특한 공간"),
        Accommodations(dummyImage, "자연생활을 만끽할 수\n있는 숙소"),
        Accommodations(dummyImage, "독특한 공간"),
        Accommodations(dummyImage, "자연생활을 만끽할 수\n있는 숙소"),
        Accommodations(dummyImage, "독특한 공간")
    )

    override fun getSearchWordList(searchWord: String): Flow<List<Travel>> = flow {
        val searchList = locations.filter {
            it.name.contains(searchWord)
        }
        emit(searchList)
    }

    override fun getTravelLocations(): Flow<List<Travel>> = flow {
        emit(travelList)
    }

    override fun getAccommodations(): Flow<List<Accommodations>> = flow {
        emit(accommodationList)
    }
}