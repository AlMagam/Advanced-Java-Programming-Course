package gaborets.lab3_fx.tasktwo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservList {
    public static void main(String[] args) {
        final ObservableList<Double> list = FXCollections.observableArrayList(-4.5, 2.2, -15.0, 3.0, -1.1, 6.1, -4.2);
        // listener
        list.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("Numbers were added " + list);
                        return;
                    }else if (change.wasRemoved()) {
                        System.out.println("List was cleared " + list);
                        return;
                    }
                    System.out.println("Detected changes " + list);
                }
            }
        });
        System.out.println("Start list " + list);
        // Create two new lists for positive and negative numb from first_list
        final ObservableList<Double> listWithPositive = FXCollections.observableArrayList();
        final ObservableList<Double> listWithNegative = FXCollections.observableArrayList();
        //Iterate orig list und add positiv numb to listWithPositive and negative numb to listWithNegative
        for (Double i : list) {
            if (i >= 0) {
                listWithPositive.add(i);
            } else {
                listWithNegative.add(i);
            }
        }
        list.clear();
        list.addAll(listWithPositive);
        for (int i = listWithNegative.size() - 1; i >= 0; i--) {
            list.add(listWithNegative.get(i));
        }
        System.out.println("Final list " + list);
    }
}
