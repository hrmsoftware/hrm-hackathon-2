package hrmsoftware.se.trashfriendwidget;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrashFriendService {

    @GET("now")
    Call<Status> now();

}
