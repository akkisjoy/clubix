package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.adapter.EventListAdapter;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.server.Client;
import com.prolificwebworks.theclubix.utils.DialogShower;
import com.prolificwebworks.theclubix.utils.EventTime;
import com.prolificwebworks.theclubix.utils.MyEnum;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/7/2015.
 */
public class EventSubSection extends Fragment {


    EventTime eventTime;
    RecyclerView recyclerView;
    DialogShower constants;



    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public static EventSubSection newInstance() {
        EventSubSection fragment = new EventSubSection();

        return fragment;
    }


    public EventSubSection() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.event_subsection, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        constants = new DialogShower(getActivity());
        constants.show();

        callServiceTogetData(eventTime);


        return v;
    }

    private void callServiceTogetData(EventTime eventTime) {


        switch (eventTime) {
            case TODAY:

                if (MyEnum.INSTANCE.getToday() == null || MyEnum.INSTANCE.getToday().size() == 0) {


                    Client.INSTANCE.getTodaysEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {


                            constants.dismiss();

                            MyEnum.INSTANCE.setToday(allEvents.getPostData());
                            setAdapterView(EventTime.TODAY);


                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    constants.dismiss();
                    setAdapterView(EventTime.TODAY);
                }


                if (MyEnum.INSTANCE.getTomorrow() == null || MyEnum.INSTANCE.getTomorrow().size() == 0) {

                    Client.INSTANCE.getTomorrowEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            MyEnum.INSTANCE.setTomorrow(allEvents.getPostData());

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                }
                break;
            case TOMORROW:

                if (MyEnum.INSTANCE.getTomorrow() == null || MyEnum.INSTANCE.getTomorrow().size() == 0) {


                    Client.INSTANCE.getTomorrowEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            constants.dismiss();

                            MyEnum.INSTANCE.setTomorrow(allEvents.getPostData());
                            setAdapterView(EventTime.TOMORROW);

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    constants.dismiss();
                    setAdapterView(EventTime.TOMORROW);

                }

                if (MyEnum.INSTANCE.getLater() == null || MyEnum.INSTANCE.getLater().size() == 0) {


                    Client.INSTANCE.getLaterEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {
                            MyEnum.INSTANCE.setLater(allEvents.getPostData());

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                }


                break;
            case LATER:


                if (MyEnum.INSTANCE.getLater() == null || MyEnum.INSTANCE.getLater().size() == 0) {


                    Client.INSTANCE.getLaterEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            constants.dismiss();

                            MyEnum.INSTANCE.setLater(allEvents.getPostData());
                            setAdapterView(EventTime.LATER);

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    constants.dismiss();
                    setAdapterView(EventTime.LATER);

                }

                if (MyEnum.INSTANCE.getTomorrow() == null || MyEnum.INSTANCE.getTomorrow().size() == 0) {

                    Client.INSTANCE.getTomorrowEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            MyEnum.INSTANCE.setTomorrow(allEvents.getPostData());

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                }

                break;
            default:

                if (MyEnum.INSTANCE.getToday() == null || MyEnum.INSTANCE.getToday().size() == 0) {


                    Client.INSTANCE.getTodaysEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            constants.dismiss();

                            MyEnum.INSTANCE.setToday(allEvents.getPostData());
                            setAdapterView(EventTime.TODAY);


                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                } else {
                    constants.dismiss();
                    setAdapterView(EventTime.TODAY);
                }


                if (MyEnum.INSTANCE.getTomorrow() == null || MyEnum.INSTANCE.getTomorrow().size() == 0) {

                    Client.INSTANCE.getTomorrowEvents(new Callback<AllEvents>() {
                        @Override
                        public void success(AllEvents allEvents, Response response) {

                            MyEnum.INSTANCE.setTomorrow(allEvents.getPostData());

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                }

        }


    }

    public void setAdapterView(EventTime eventDatas) {
        EventListAdapter eventListAdapter = new EventListAdapter(EventSubSection.this, eventDatas);
        recyclerView.setAdapter(eventListAdapter);
    }
}
