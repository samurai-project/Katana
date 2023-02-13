package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.model.messenger.Message
import dev.nmgalo.core.model.messenger.MessageStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeMessengerRepository @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO) private val io: CoroutineDispatcher,
) : MessengerRepository {

    @Suppress("LongMethod")
    override fun getMessagesByChatId(conversationId: Long): Flow<List<Message>> = flow {
        emit(
            listOf(
                Message(
                    id = 1,
                    message = "გამარჯობა ნიკა",
                    senderId = 1,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 2,
                    message = "სალამი",
                    senderId = 2,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 3,
                    message = "როგორ ხარ?",
                    senderId = 1,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 4,
                    message = "რავი არამიშავს, ვარ ნელნელა, უკეთესადაც ვყოფლვარ",
                    senderId = 2,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 5,
                    message = "მაგრად უნდა იდგე ლომოო",
                    senderId = 1,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 6,
                    message = "ხო იასნია",
                    senderId = 2,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 6,
                    message = "ხანდახან ძალიან მარტივია ტექსტის დაგრძელება, თუ რენდომ რამეებს აკფრეფ " +
                            "კლაიატურაზე, თანაც შემეძლება შევამოწმო გრძელ გადაბმულ სტრინგზე როგორ იქცება აპი," +
                            " კარგი გზაა ისე ამის შესამოწმებლად",
                    senderId = 1,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 7,
                    message = "დასდაკჯდასკდჯსადკაფგოსდფლასდჯფკლსდასდაკჯდასკდჯსადკაფგოსდფლასდჯფკლსდას" +
                            "დაკჯდასკდჯსადკაფგოსდფლასდჯფკლს",
                    senderId = 2,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 8,
                    message = "მგონი საკმარისია, ან რავი, ცოტა კიდევ დავამატოთ ჩვენ მიმოწერას, " +
                            "არამგონია სქროლვადი კონტენტი იყოს ჯერჯერობით, ანდაც იქნება",
                    senderId = 1,
                    status = MessageStatus.READ,
                    createdAt = 1
                ),
                Message(
                    id = 9,
                    message = "ხო, საკმარისია აწი, სასიამოვნო დღეს გისყურვებ, არ დაგავიწყდეს, " +
                            "მცირედ დაწერილ კოდსაც დიდი ძალა აქვს <3",
                    senderId = 2,
                    status = MessageStatus.SENT,
                    createdAt = 1
                ),
            ).sortedByDescending { it.id } // Same for database, We should use ORDER BY id or date DESC
        )
    }.flowOn(io)
}
