package com.casper.testdrivendevelopment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_BookList extends Fragment {

    BookArrayAdapter bookArrayAdapter;
    public Fragment_BookList(BookArrayAdapter DaAdapter) {
        this.bookArrayAdapter=DaAdapter;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_book_list, container, false);
        ListView listViewSuper= (ListView) view.findViewById(R.id.list_view_books);
        listViewSuper.setAdapter(bookArrayAdapter);

        this.registerForContextMenu(listViewSuper);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_book_list, container, false);
        return view;
    }

}
