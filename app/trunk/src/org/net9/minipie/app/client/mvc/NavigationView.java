/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.net9.minipie.app.client.AppEvents;
import org.net9.minipie.app.client.Explorer;
import org.net9.minipie.app.client.ExplorerModel;
import org.net9.minipie.app.client.model.Category;
import org.net9.minipie.app.client.model.Entry;

//import com.extjs.gxt.samples.resources.client.model.Folder;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.binder.DataListBinder;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionService;
import com.extjs.gxt.ui.client.event.SourceSelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.TabPanel.TabPosition;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class NavigationView extends View {

  private ExplorerModel model;
  private ContentPanel westPanel;
  private ToolBar toolBar;
  private TabPanel tabPanel;
  private TabItem listItem, treeItem;
  private TreePanel<ModelData> tree;

  private DataList dataList;
  private TreeStore<ModelData> treeStore;
  private StoreFilterField<ModelData> filter;

  public NavigationView(Controller controller) {
    super(controller);
  }

  protected void initialize() {
    model = (ExplorerModel) Registry.get(Explorer.MODEL);
    SelectionService.get().addListener(new SelectionChangedListener<TreeModel>() {
      public void selectionChanged(SelectionChangedEvent<TreeModel> event) {
        List<TreeModel> sel = event.getSelection();
        if (sel.size() > 0) {
          TreeModel m = (TreeModel) event.getSelection().get(0);
          if (m != null && m instanceof Entry) {
            Explorer.showPage((Entry) m);
          }
        }
      }
    });

    filter = new StoreFilterField<ModelData>() {

      @Override
      protected boolean doSelect(Store<ModelData> store, ModelData parent,
          ModelData child, String property, String filter) {
        //if (child instanceof Folder) {
        if (child instanceof Category) {
          return false;
        }
        String name = child.get("name");
        name = name.toLowerCase();
        if (name.indexOf(filter.toLowerCase()) != -1) {
          return true;
        }
        return false;

      }

    };

    westPanel = (ContentPanel) Registry.get(AppView.WEST_PANEL);
    westPanel.setHeading("Navigation");
    westPanel.setLayout(new FitLayout());
    westPanel.add(createTabPanel());

    toolBar = (ToolBar) westPanel.getTopComponent();
    IconButton filterBtn = new IconButton("icon-filter");
    filterBtn.setWidth(20);
    toolBar.add(filterBtn);
    toolBar.add(filter);

    createListContent();
    createTreeContent();
    westPanel.syncSize();
  }

  private TabPanel createTabPanel() {
    tabPanel = new TabPanel();
    tabPanel.setMinTabWidth(70);
    tabPanel.setBorderStyle(false);
    tabPanel.setBodyBorder(false);
    tabPanel.setTabPosition(TabPosition.BOTTOM);

    treeItem = new TabItem();
    treeItem.setText("Tree");
    tabPanel.add(treeItem);

    listItem = new TabItem();
    listItem.setText("List");
    tabPanel.add(listItem);

    return tabPanel;
  }

  private void createTreeContent() {
    TreeLoader<ModelData> loader = new BaseTreeLoader<ModelData>(new TreeModelReader<List<ModelData>>()) {
      @Override
      public boolean hasChildren(ModelData parent) {
        return parent instanceof Category;
      }
      
    };
    treeStore = new TreeStore<ModelData>(loader);

    tree = new TreePanel<ModelData>(treeStore);
    tree.getStyle().setLeafIconStyle("icon-list");
    tree.setAutoLoad(true);
    tree.setDisplayProperty("name");

    SelectionService.get().addListener(new SourceSelectionChangedListener(tree.getSelectionModel()));
    SelectionService.get().register(tree.getSelectionModel());

    filter.bind(treeStore);
    loader.load(model);

    treeItem.setBorders(false);
    treeItem.setScrollMode(Scroll.AUTO);
    treeItem.add(tree);
  }

  @SuppressWarnings("unchecked")
  private void createListContent() {
    listItem.setLayout(new FitLayout());
    listItem.setBorders(false);

    dataList = new DataList();
    dataList.setScrollMode(Scroll.AUTO);
    dataList.setBorders(false);
    dataList.setFlatStyle(true);

    ListStore<Entry> store = new ListStore<Entry>();
    store.setStoreSorter(new StoreSorter(new Comparator<Entry>() {

      public int compare(Entry e1, Entry e2) {
        return e1.getName().compareTo(e2.getName());
      }

    }));
    store.add(model.getEntries());

    DataListBinder binder = new DataListBinder(dataList, store);
    binder.setDisplayProperty("name");
    binder.addSelectionChangedListener(new SelectionChangedListener<Entry>() {
      public void selectionChanged(SelectionChangedEvent<Entry> se) {
        Entry e = se.getSelection().get(0);
        if (e != null && e instanceof Entry) {
          Explorer.showPage(e);
        }
      }
    });
    binder.init();
    filter.bind((Store) store);
    listItem.add(dataList);
  }

  @SuppressWarnings("unchecked")
  protected void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type == AppEvents.HidePage) {
      tree.getSelectionModel().setSelection(new ArrayList<ModelData>());
    } else if (type == AppEvents.TabChange) {
      if (((Entry) event.getData()).getName() == "Overview") {
        tree.getSelectionModel().setSelection(new ArrayList<ModelData>());
      } else {
        tree.getSelectionModel().setSelection((List)Arrays.asList((Entry) event.getData()));
      }
    }
  }

}
