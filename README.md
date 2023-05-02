# RawTabLayoutDemo





Lab – Creating a Tabbed Interface using a TabLayout Component
Objectives

    Understand and implement TabLayout
    Understand and implement ViewPager
    Link ViewPager with TabLayout
    Utilize FragmentStateAdapter to control tab navigation
    Link tab layout with view pager through TabLayoutMediator


Binding is not used in this lab. That is why the project is called “raw” Tab Layout demonstration.

Part 1:
Step 1: Create a new project, RawTabLayoutDemo, using Empty Activity
Step 2: Open activity_main.xml
Step 2.1: Delete “Hello World!” TextView in Code View
Step 2.2: Go to Design mode
Step 2.3: Click Containers in Palette
Step 2.4: Drag TabLayout into Component Tree under ConstraintLayou
If IDE asks to add Project Dependency, you just click OK button. And wait for it to be done.
Step 2.5: Now there might be an error associated with TabLayout about the constraint. Select TabLayout by clicking on it in the Component Tree, then click the magic wand (i.e., Infer Constraints) on the top of the editor. You will see the error is fixed. And the id for the TabLayout is “tabLayout”.
Step 3: Run the app to see the tabs
You can click on the tabs to see how they work

This shows you that you can define tabs in the layout file (See the TabItems defined in activity_main.xml. They are defined as com.google.android.material.tabs.TabItem.)



Step 4: Delete these three TabItems in the layout file but still leave TabLayout there.
You will see no tabs if you run the app now.
Step 5: Open MainActivity.java and create a private variable as follows
TabLayout tabLayout;
When you type TabLay, the system will prompt you and you will select “com.google.android.material.tabs” and make sure you do not select android.widget. Double check on this. If you select the wrong one, your app won’t perform as planned.
Finish the code as:
TabLayout tabLayout;
Type in the following code following setContentView() in onCreate().

tabLayout = findViewById(R.id.tabLayout);

tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));

tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));

tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));


Step 6: You can run app to see your work
Three tabs (i.e., TAB 1, TAB 2, and TAB 3) are created. You can click on each tab.
So far we have shown two approaches to create the tabs: in the layout file and through the java file.
Step 7: Open activity_main.xml in Design mode
Step 8: Drag ViewPager2 under Containers in Palette to Component Tree and place it under tabLayout
Step 9: Set up attributes for ViewPager2
Define the following attributes in the Attributes panel on the right-hand side while ViewPager2 is selected in the Component Tree
id: viewpager
layout_height: wrap_content
layout_constraintTop_toBottomOf: select @id/tabLayout
Set up the right margin and left margin in Constraint Widget to be 0 if they are not.
Step 10: you can run your app to verify your interface design.
Part 2:
Step 11: Create a blank fragment and name it as “Frag1” and change the text for its TextView in the corresponding layout file to be “Fragment 1”
Step 12: Create a blank fragment and name it as “Frag2” and change the text for its TextView in the corresponding layout file to be “Fragment 2”
Step 13: Create a blank fragment and name it as “Frag3” and change the text for its TextView in the corresponding layout file to be “Fragment 3”
Step 14: Comment the three newTab() lines in MainActivity.java as follows

//tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));


Step 15: Create a Java class and name it as “FragmentStateAdapter”.
This will be an adapter class and we extend the class as a FragmentStateAdapter.
Thus, the class declaration should look like as follows:

public class ViewPagerAdapter extends FragmentStateAdapter {}



You find the IDE is not happy (due to the interface). How to know that? The red line under the class declaration and the red lightbulb on the left-hand side.
Click on the red lightbulb and select “Implement methods”
System will show a dialog with the title as “Select Methods to Implement”.
These two methods, createFragment() and getItemCount(), are highlighted for implementation.
Click OK button.
These two methods are created.
System is still not happy because we need a constructor for this newly created class.
Click the red lightbulb on the left-hand side and select “Create constructor matching super”
If you don’t see the lightbulb, you just click on the red line to make it appear.
Select the constructor with FragmentManager and Lifecycle as its two parameters.
The IDE will create the constructor as follows:
public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
super(fragmentManager, lifecycle);
}

createFragment() will create a fragment based on the index in the collection of the fragments.
Let’s change createFragment() as follows:
public Fragment createFragment(int position) {
if (position == 0)
return new Frag1();
else if (position == 1)
return new Frag2();
return new Frag3();
}

getItemCount() returns the total number of the tabs.
Let’s change the getItemCount() as follows:
public int getItemCount() {
return 3;
}

Thus, the class, FragmentStateAdapter.java, should look like the following:

package com.example.rawtablayoutdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

public class FragmentStateAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
super(fragmentManager, lifecycle);
}

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new Frag1();
        else if (position == 1)
            return new Frag2();
        return new Frag3();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

Step 16: Open MainActivity.java
Declare two private variables as follows:
ViewPager2 viewPager;
FragmentStateAdapter viewPagerAdapter;



Pay attention we use ViewPager2 as the type above.

Alspo the FragmentStateAdapter is the class that we declare in the package. Make sure you import the right class.


And put in the following code toward the end of onCreate()
viewPager = findViewById(R.id.viewpager);
viewPagerAdapter = new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()); // Use the one defined in this package
viewPager.setAdapter(viewPagerAdapter);
new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("TAB " + (position + 1))).attach();

Again, when you new FragmentStateAdapter above you need to choose the FragmentStateAdapter class in this package.

If your code is correct but the app does not execute then most of the time you import the wrong library.
Check the libraries I import in the listing below.

Thus, MainActivity.java should look like:
package com.example.rawtablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;

    ViewPager2 viewPager;
    FragmentStateAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()); // Use the one defined in this package
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("TAB " + (position + 1))).attach();
    }
}

We use our defined FragmentStateAdapter to polulate frgaments to the view pager.
Use TabLayoutMediator to link the TabLayout with view pager to display the title for each tab.

Step 17: You can run your app and click on each tab to show its corresponding fragment.
Step 18: Take a screenshot for each tab.

Submit the three screenshots you take at Step 18 with MainActivity.java.