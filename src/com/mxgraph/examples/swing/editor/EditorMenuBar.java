package com.mxgraph.examples.swing.editor;

import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.xml.bind.JAXBException;

import com.mxgraph.analysis.StructuralException;
import com.mxgraph.analysis.mxGraphProperties.GraphType;
import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.analysis.mxGraphProperties;
import com.mxgraph.analysis.mxGraphStructure;
import com.mxgraph.analysis.mxTraversal;
import com.mxgraph.costfunction.mxCostFunction;
import com.mxgraph.examples.language.SystemLang;
import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.examples.swing.editor.EditorActions.AlignCellsAction;
import com.mxgraph.examples.swing.editor.EditorActions.AutosizeAction;
import com.mxgraph.examples.swing.editor.EditorActions.BackgroundAction;
import com.mxgraph.examples.swing.editor.EditorActions.BackgroundImageAction;
import com.mxgraph.examples.swing.editor.EditorActions.ColorAction;
import com.mxgraph.examples.swing.editor.EditorActions.ExitAction;
import com.mxgraph.examples.swing.editor.EditorActions.GridColorAction;
import com.mxgraph.examples.swing.editor.EditorActions.GridStyleAction;
import com.mxgraph.examples.swing.editor.EditorActions.HistoryAction;
import com.mxgraph.examples.swing.editor.EditorActions.ImportAction;
import com.mxgraph.examples.swing.editor.EditorActions.KeyValueAction;
import com.mxgraph.examples.swing.editor.EditorActions.NewAction;
import com.mxgraph.examples.swing.editor.EditorActions.OpenAction;
import com.mxgraph.examples.swing.editor.EditorActions.PageBackgroundAction;
import com.mxgraph.examples.swing.editor.EditorActions.PageSetupAction;
import com.mxgraph.examples.swing.editor.EditorActions.PrintAction;
import com.mxgraph.examples.swing.editor.EditorActions.PromptPropertyAction;
import com.mxgraph.examples.swing.editor.EditorActions.PromptValueAction;
import com.mxgraph.examples.swing.editor.EditorActions.SaveAction;
import com.mxgraph.examples.swing.editor.EditorActions.ScaleAction;
import com.mxgraph.examples.swing.editor.EditorActions.SelectShortestPathAction;
import com.mxgraph.examples.swing.editor.EditorActions.SelectSpanningTreeAction;
import com.mxgraph.examples.swing.editor.EditorActions.SetLabelPositionAction;
import com.mxgraph.examples.swing.editor.EditorActions.SetStyleAction;
import com.mxgraph.examples.swing.editor.EditorActions.StyleAction;
import com.mxgraph.examples.swing.editor.EditorActions.StylesheetAction;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleAction;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleConnectModeAction;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleCreateTargetItem;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleDirtyAction;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleGridItem;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleOutlineItem;
import com.mxgraph.examples.swing.editor.EditorActions.TogglePropertyItem;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleRulersItem;
import com.mxgraph.examples.swing.editor.EditorActions.WarningAction;
import com.mxgraph.examples.swing.editor.EditorActions.ZoomPolicyAction;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class EditorMenuBar extends JMenuBar
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4060203894740766714L;
	
	/************Changed by bdlions***************/
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu viewMenu;
	private JMenu formatMenu;
	private JMenu shapeMenu;
	private JMenu diagramMenu;
	private JMenu backGroundSubmenu;
	private JMenu layoutSubmenu;
	private JMenu selectionSubmenu;
	private JMenu styleSubmenu;
	private JMenu optionMenu;
	private JMenu displaySubmenu;
	private JMenu zoomSubmenu;
	private JMenu dragAndDropSubmenu;
	private JMenu labelsSubmenu;
	private JMenu connectionsSubmenu;
	private JMenu windowMenu;
	private JMenu analyzeMenu;
	private JMenu generateMenu;
	private JMenu helpMenu;
	
	/*all file menu sub menu*/
	private JMenu file_menu_item_new;
	private JMenu file_menu_item_open_new;
	private JMenuItem file_menu_item_open;
	private JMenuItem file_menu_item_import;
	private JMenuItem file_menu_item_save;
	private JMenuItem file_menu_item_save_as;
	private JMenuItem file_menu_item_page_setup;
	private JMenuItem file_menu_item_print;
	private JMenuItem file_menu_item_exit;
	
	/*all edit sub menu*/
	private JMenuItem edit_menu_item_undo;
	private JMenuItem edit_menu_item_redo;
	private JMenuItem edit_menu_item_cut;
	private JMenuItem edit_menu_item_copy;
	private JMenuItem edit_menu_item_paste;
	private JMenuItem edit_menu_item_delete;
	private JMenuItem edit_menu_item_select_all;
	private JMenuItem edit_menu_item_select_none;
	private JMenuItem edit_menu_item_warning;
	
	/*all view menu*/
	private JMenuItem view_menu_item_page_layout;
	private JMenuItem view_menu_item_antialias;
	private JMenuItem view_menu_item_grid;
	private JMenuItem view_menu_item_rulers;
	private JMenu view_menu_item_zoom;
	private JMenuItem view_menu_item_zoomin;
	private JMenuItem view_menu_item_zoomout;
	private JMenuItem view_menu_item_page;
	private JMenuItem view_menu_item_width;
	private JMenuItem view_menu_item_actual_size;
	private JMenuItem view_menu_item_custom;
	
	/*all format menu item*/
	private static JMenu format_menu_item_background;
	private static JMenuItem format_background_menu_item_fill_color;
	private static JMenuItem format_background_menu_item_gradient;
	private static JMenuItem format_background_menu_item_image;
	private static JMenuItem format_background_menu_item_shadow;
	private static JMenuItem format_background_menu_item_opacity;
	
	private static JMenu format_menu_item_label;
	private static JMenuItem format_label_menu_item_font_color;
	private static JMenuItem format_label_menu_item_label_fill;
	private static JMenuItem format_label_menu_item_label_border;
	private static JMenuItem format_label_menu_item_rotate_label;
	private static JMenuItem format_label_menu_item_text_opacity;
	private static JMenu format_label_menu_item_position;
	private static JMenuItem format_label_menu_item_word_wrap;
	private static JMenuItem format_label_menu_item_no_word_wrap;
	private static JMenuItem format_label_menu_item_hide;
	
	private static JMenu format_menu_item_line;
	private static JMenuItem format_line_menu_item_line_color;
	private static JMenuItem format_line_menu_item_orthogonal;
	private static JMenuItem format_line_menu_item_dashed;
	private static JMenuItem format_line_menu_item_line_width;
	
	private static JMenu format_menu_item_connector;
	private static JMenuItem format_connector_menu_item_straight;
	private static JMenuItem format_connector_menu_item_horizontal;
	private static JMenuItem format_connector_menu_item_vertical;
	private static JMenuItem format_connector_menu_item_entity_relation;
	private static JMenuItem format_connector_menu_item_arrow;
	private static JMenuItem format_connector_menu_item_plain;
	
	private static JMenu format_menu_item_line_start;
	private static JMenuItem format_line_start_menu_item_open;
	private static JMenuItem format_line_start_menu_item_classic;
	private static JMenuItem format_line_start_menu_item_block;
	private static JMenuItem format_line_start_menu_item_diamond;
	private static JMenuItem format_line_start_menu_item_oval;
	private static JMenuItem format_line_start_menu_item_none;
	private static JMenuItem format_line_start_menu_item_size;
	
	private static JMenu format_menu_item_lineed;
	private static JMenuItem format_linned_menu_item_open;
	private static JMenuItem format_linned_menu_item_classic;
	private static JMenuItem format_linned_menu_item_block;
	private static JMenuItem format_linned_menu_item_diamond;
	private static JMenuItem format_linned_menu_item_oval;
	private static JMenuItem format_linned_menu_item_none;
	private static JMenuItem format_linned_menu_item_size;
	
	private static JMenu format_menu_item_alignment;
	private static JMenuItem format_alignment_menu_item_left;
	private static JMenuItem format_alignment_menu_item_center;
	private static JMenuItem format_alignment_menu_item_right;
	private static JMenuItem format_alignment_menu_item_top;
	private static JMenuItem format_alignment_menu_item_middle;
	private static JMenuItem format_alignment_menu_item_bottom;
	
	private static JMenu format_menu_item_spacing;
	private static JMenuItem format_spacing_menu_item_top;
	private static JMenuItem format_spacing_menu_item_right;
	private static JMenuItem format_spacing_menu_item_bottom;
	private static JMenuItem format_spacing_menu_item_left;
	private static JMenuItem format_spacing_menu_item_global;
	private static JMenuItem format_spacing_menu_item_source_spacing;
	private static JMenuItem format_spacing_menu_item_target_spacing;
	private static JMenuItem format_spacing_menu_item_perimeter;
	
	private static JMenu format_menu_item_direction;
	private static JMenuItem format_direction_menu_item_north;
	private static JMenuItem format_direction_menu_item_east;
	private static JMenuItem format_direction_menu_item_south;
	private static JMenuItem format_direction_menu_item_west;
	private static JMenuItem format_direction_menu_item_rotation;
	
	private static JMenuItem format_menu_item_rounded;
	private static JMenuItem format_menu_item_style;
	
	/*all shape menu item*/
	private static JMenuItem shape_menu_item_exitGroup;
	private static JMenuItem shape_menu_item_enterGroup;
	private static JMenuItem shape_menu_item_home;
	private static JMenuItem shape_menu_item_group;
	private static JMenuItem shape_menu_item_unGroup;
	private static JMenuItem shape_menu_item_removeFromGroup;
	private static JMenuItem shape_menu_item_updateGroupBounds;
	private static JMenuItem shape_menu_item_collapse;
	private static JMenuItem shape_menu_item_expand;
	private static JMenuItem shape_menu_item_toBack;
	private static JMenuItem shape_menu_item_toFront;
	private static JMenuItem shape_menu_item_autoSize;
	
	
	private static EditorPalette familiesPalette;
	private static EditorPalette homePalette;
	private static EditorPalette processPalette;
	public static String selectedPalette;
	
	private static SaveAction saveAction;
	private static SaveAction saveAsAction;
	private static OpenAction openAction;
	
	public enum Palette {
		FAMILIES("Families", 1),
		HOME("Home", 2),
		PROCESS("Process", 3)
		;
		/**
		 * @param text
		 */
		private Palette(final String text, int pos) {
			this.text = text;
			this.pos = pos;
		}

		private final String text;
		@Override
		public String toString() {
			return text;
		}
		
		private final int pos;
		
		public int toNumber(){
			return pos;
		}
	}

	private static BasicGraphEditor editor;
	/************Changed by bdlions***************/

	public enum AnalyzeType
	{
		IS_CONNECTED, IS_SIMPLE, IS_CYCLIC_DIRECTED, IS_CYCLIC_UNDIRECTED, COMPLEMENTARY, REGULARITY, COMPONENTS, MAKE_CONNECTED, MAKE_SIMPLE, IS_TREE, ONE_SPANNING_TREE, IS_DIRECTED, GET_CUT_VERTEXES, GET_CUT_EDGES, GET_SOURCES, GET_SINKS, PLANARITY, IS_BICONNECTED, GET_BICONNECTED, SPANNING_TREE, FLOYD_ROY_WARSHALL
	}

	public EditorMenuBar(final BasicGraphEditor editor)
	{
		this.editor = editor;
		final mxGraphComponent graphComponent = editor.getGraphComponent();
		final mxGraph graph = graphComponent.getGraph();
		mxAnalysisGraph aGraph = new mxAnalysisGraph();

		JMenu menu = null;
		JMenu submenu = null;

		/************Changed by  bdlions***************************/
		// Creates the file menu
		//fileMenu = add(new JMenu(mxResources.get("file")));
		//file_menu_item_new = new JMenuItem();
		//file_menu_item_new.setAction(editor.bind(mxResources.get("new"), new NewAction(), "/com/mxgraph/examples/swing/images/new.gif"));
		
		fileMenu = add(new JMenu("file"));
		
		file_menu_item_new = new JMenu();
		file_menu_item_new.setAction(editor.bind(mxResources.get("new"), new NewAction(this), "/com/mxgraph/examples/swing/images/new.gif"));
		
		// here add sub menu
		fileMenu.add(file_menu_item_new);

	    JMenuItem type1 = fileMenu.add(new JMenuItem( mxResources.get("Type1") ));
	    type1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				clearPalette();
				insertFamilyPalette();
			}
		});
	    
	    JMenuItem type2 = fileMenu.add(new JMenuItem( mxResources.get("Type2") ));
	    type2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				// TODO Auto-generated method stub
				clearPalette();
				insertHomePalette();
			}
		});
		
	    JMenuItem type3 = fileMenu.add(new JMenuItem( mxResources.get("Type3") ));
	    type3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				// TODO Auto-generated method stub
				clearPalette();
				insertProcessPalette();
			}
		});
		
	    
	    file_menu_item_new.add(type1);
	    file_menu_item_new.add(type2);
	    file_menu_item_new.add(type3);
	    
	    
		/*openAction = new OpenAction();
		
		file_menu_item_open = new JMenuItem();
		file_menu_item_open.setAction(editor.bind(mxResources.get("openFile"), openAction, "/com/mxgraph/examples/swing/images/open.gif"));
		fileMenu.add(file_menu_item_open);*/
	    
	    openAction = new OpenAction();
		
		file_menu_item_open = new JMenu();
		file_menu_item_open.setAction(editor.bind(mxResources.get("openFile"), openAction, "/com/mxgraph/examples/swing/images/open.gif"));
		fileMenu.add(file_menu_item_open);

		JMenuItem openType1 = fileMenu.add(new JMenuItem( mxResources.get("Type1") ));
		openType1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				openAction.paletteType = Palette.FAMILIES.toNumber();
				openAction.openFile(editor);
			}
		});
		
		
		JMenuItem openType2 = fileMenu.add(new JMenuItem( mxResources.get("Type2") ));
		openType2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				openAction.paletteType = Palette.HOME.toNumber();
				openAction.openFile(editor);
			}
		});
		
		JMenuItem openType3 = fileMenu.add(new JMenuItem( mxResources.get("Type3") ));
		openType3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				openAction.paletteType = Palette.PROCESS.toNumber();
				openAction.openFile(editor);
			}
		});
		
		file_menu_item_open.add(openType1);
		file_menu_item_open.add(openType2);
		file_menu_item_open.add(openType3);
		
		
		
		file_menu_item_import = new JMenuItem();
		file_menu_item_import.setAction(editor.bind(mxResources.get("importStencil"), new ImportAction(), "/com/mxgraph/examples/swing/images/open.gif"));
		fileMenu.add(file_menu_item_import);
		
		fileMenu.addSeparator();

		saveAction = new SaveAction(false);
		saveAsAction = new SaveAction(true);
		
		file_menu_item_save = new JMenuItem();
		file_menu_item_save.setAction(editor.bind(mxResources.get("save"), saveAction, "/com/mxgraph/examples/swing/images/save.gif"));
		fileMenu.add(file_menu_item_save);
		
		file_menu_item_save_as = new JMenuItem();
		file_menu_item_save_as.setAction(editor.bind(mxResources.get("saveAs"), saveAsAction, "/com/mxgraph/examples/swing/images/saveas.gif"));
		fileMenu.add(file_menu_item_save_as);

		fileMenu.addSeparator();

		file_menu_item_page_setup = new JMenuItem();
		file_menu_item_page_setup.setAction(editor.bind(mxResources.get("pageSetup"), new PageSetupAction(), "/com/mxgraph/examples/swing/images/pagesetup.gif"));
		fileMenu.add(file_menu_item_page_setup);
		
		file_menu_item_print = new JMenuItem();
		file_menu_item_print.setAction(editor.bind(mxResources.get("print"), new PrintAction(), "/com/mxgraph/examples/swing/images/print.gif"));
		fileMenu.add(file_menu_item_print);

		fileMenu.addSeparator();

		file_menu_item_exit = new JMenuItem();
		file_menu_item_exit.setAction(editor.bind(mxResources.get("exit"), new ExitAction()));
		fileMenu.add(file_menu_item_exit);

		// Creates the edit menu
		editMenu = add(new JMenu(mxResources.get("edit")));

		edit_menu_item_undo = new JMenuItem();
		edit_menu_item_undo.setAction(editor.bind(mxResources.get("undo"), new HistoryAction(true), "/com/mxgraph/examples/swing/images/undo.gif"));
		editMenu.add(edit_menu_item_undo);
		
		edit_menu_item_redo = new JMenuItem();
		edit_menu_item_redo.setAction(editor.bind(mxResources.get("redo"), new HistoryAction(false), "/com/mxgraph/examples/swing/images/redo.gif"));
		editMenu.add(edit_menu_item_redo);

		editMenu.addSeparator();

		edit_menu_item_cut = new JMenuItem();
		edit_menu_item_cut.setAction(editor.bind(mxResources.get("cut"), TransferHandler.getCutAction(), "/com/mxgraph/examples/swing/images/cut.gif"));
		editMenu.add(edit_menu_item_cut);
		
		edit_menu_item_copy = new JMenuItem();
		edit_menu_item_copy.setAction(editor.bind(mxResources.get("copy"), TransferHandler.getCopyAction(), "/com/mxgraph/examples/swing/images/copy.gif"));
		editMenu.add(edit_menu_item_copy);
		
		edit_menu_item_paste = new JMenuItem();
		edit_menu_item_paste.setAction(editor.bind(mxResources.get("paste"), TransferHandler.getPasteAction(), "/com/mxgraph/examples/swing/images/paste.gif"));
		editMenu.add(edit_menu_item_paste);

		editMenu.addSeparator();

		edit_menu_item_delete = new JMenuItem();
		edit_menu_item_delete.setAction(editor.bind(mxResources.get("delete"), mxGraphActions.getDeleteAction(), "/com/mxgraph/examples/swing/images/delete.gif"));
		editMenu.add(edit_menu_item_delete);

		editMenu.addSeparator();

		edit_menu_item_select_all = new JMenuItem();
		edit_menu_item_select_all.setAction(editor.bind(mxResources.get("selectAll"), mxGraphActions.getSelectAllAction()));
		editMenu.add(edit_menu_item_select_all);
		
		edit_menu_item_select_none = new JMenuItem();
		edit_menu_item_select_none.setAction(editor.bind(mxResources.get("selectNone"), mxGraphActions.getSelectNoneAction()));
		editMenu.add(edit_menu_item_select_none);

		editMenu.addSeparator();

		edit_menu_item_warning = new JMenuItem();
		edit_menu_item_warning.setAction(editor.bind(mxResources.get("warning"), new WarningAction()));
		editMenu.add(edit_menu_item_warning);
		
		//editMenu.add(editor.bind(mxResources.get("edit"), mxGraphActions.getEditAction()));

		// Creates the view menu
		viewMenu = add(new JMenu(mxResources.get("view")));

		view_menu_item_page_layout = viewMenu.add(new TogglePropertyItem(graphComponent, mxResources.get("pageLayout"), "PageVisible", true,
				new ActionListener()
				{
					/**
					 * 
					 */
					public void actionPerformed(ActionEvent e)
					{
						if (graphComponent.isPageVisible() && graphComponent.isCenterPage())
						{
							graphComponent.zoomAndCenter();
						}
						else
						{
							graphComponent.getGraphControl().updatePreferredSize();
						}
					}
				}));

		view_menu_item_page_layout.addActionListener(new ActionListener()
		{
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() instanceof TogglePropertyItem)
				{
					final mxGraphComponent graphComponent = editor.getGraphComponent();
					TogglePropertyItem toggleItem = (TogglePropertyItem) e.getSource();

					if (toggleItem.isSelected())
					{
						// Scrolls the view to the center
						SwingUtilities.invokeLater(new Runnable()
						{
							/*
							 * (non-Javadoc)
							 * @see java.lang.Runnable#run()
							 */
							public void run()
							{
								graphComponent.scrollToCenter(true);
								graphComponent.scrollToCenter(false);
							}
						});
					}
					else
					{
						// Resets the translation of the view
						mxPoint tr = graphComponent.getGraph().getView().getTranslate();

						if (tr.getX() != 0 || tr.getY() != 0)
						{
							graphComponent.getGraph().getView().setTranslate(new mxPoint());
						}
					}
				}
			}
		});

		view_menu_item_antialias = viewMenu.add(new TogglePropertyItem(graphComponent, mxResources.get("antialias"), "AntiAlias", true));

		viewMenu.addSeparator();

		view_menu_item_grid = viewMenu.add(new ToggleGridItem(editor, mxResources.get("grid")));
		view_menu_item_rulers = viewMenu.add(new ToggleRulersItem(editor, mxResources.get("rulers")));

		viewMenu.addSeparator();

		view_menu_item_zoom = (JMenu) viewMenu.add(new JMenu(mxResources.get("zoom")));

		view_menu_item_zoom.add(editor.bind("400%", new ScaleAction(4)));
		view_menu_item_zoom.add(editor.bind("200%", new ScaleAction(2)));
		view_menu_item_zoom.add(editor.bind("150%", new ScaleAction(1.5)));
		view_menu_item_zoom.add(editor.bind("100%", new ScaleAction(1)));
		view_menu_item_zoom.add(editor.bind("75%", new ScaleAction(0.75)));
		view_menu_item_zoom.add(editor.bind("50%", new ScaleAction(0.5)));

		view_menu_item_zoom.addSeparator();

		view_menu_item_custom = view_menu_item_zoom.add(editor.bind(mxResources.get("custom"), new ScaleAction(0)));

		viewMenu.addSeparator();

		view_menu_item_zoomin = viewMenu.add(editor.bind(mxResources.get("zoomIn"), mxGraphActions.getZoomInAction()));
		view_menu_item_zoomout = viewMenu.add(editor.bind(mxResources.get("zoomOut"), mxGraphActions.getZoomOutAction()));

		viewMenu.addSeparator();

		view_menu_item_page = viewMenu.add(editor.bind(mxResources.get("page"), new ZoomPolicyAction(mxGraphComponent.ZOOM_POLICY_PAGE)));
		view_menu_item_width = viewMenu.add(editor.bind(mxResources.get("width"), new ZoomPolicyAction(mxGraphComponent.ZOOM_POLICY_WIDTH)));

		viewMenu.addSeparator();

		view_menu_item_actual_size = viewMenu.add(editor.bind(mxResources.get("actualSize"), mxGraphActions.getZoomActualAction()));

		/******Changed by bdlions*********/
		menu = add(new JMenu("Language"));
		
		ResourceBundle rb = ResourceBundle.getBundle(SystemLang.getLangConfigFilePath());
		ArrayList<String> reverseList = new ArrayList<String>();
		Enumeration<String> keys = rb.getKeys();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			reverseList.add(key);
		}
		Collections.reverse(reverseList);
	    for (Object key : reverseList) {

			JMenuItem languageEnglish = menu.add(new JMenuItem( key.toString() ));
			languageEnglish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg) {
					// TODO Auto-generated method stub
					try {
						SystemLang.changeDefaultLang(arg.getActionCommand());
					} catch (FileNotFoundException | JAXBException
							| URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resetEditorMenu();
				}
			});
			
		}
	    
	    // for initially load a family pattern
	    insertFamilyPalette();
	    
		
		/******Changed by bdlions*********/
		
		
		// Creates the format menu
		formatMenu = add(new JMenu(mxResources.get("format")));

		populateFormatMenu(formatMenu, editor);

		// Creates the shape menu
		shapeMenu = add(new JMenu(mxResources.get("shape")));

		populateShapeMenu(shapeMenu, editor);

		// Creates the diagram menu
		diagramMenu = add(new JMenu(mxResources.get("diagram")));

		diagramMenu.add(new ToggleOutlineItem(editor, mxResources.get("outline")));

		diagramMenu.addSeparator();

		backGroundSubmenu = (JMenu) diagramMenu.add(new JMenu(mxResources.get("background")));

		backGroundSubmenu.add(editor.bind(mxResources.get("backgroundColor"), new BackgroundAction()));
		backGroundSubmenu.add(editor.bind(mxResources.get("backgroundImage"), new BackgroundImageAction()));

		backGroundSubmenu.addSeparator();

		backGroundSubmenu.add(editor.bind(mxResources.get("pageBackground"), new PageBackgroundAction()));

		backGroundSubmenu = (JMenu) diagramMenu.add(new JMenu(mxResources.get("grid")));

		backGroundSubmenu.add(editor.bind(mxResources.get("gridSize"), new PromptPropertyAction(graph, "Grid Size", "GridSize")));
		backGroundSubmenu.add(editor.bind(mxResources.get("gridColor"), new GridColorAction()));

		backGroundSubmenu.addSeparator();

		backGroundSubmenu.add(editor.bind(mxResources.get("dashed"), new GridStyleAction(mxGraphComponent.GRID_STYLE_DASHED)));
		backGroundSubmenu.add(editor.bind(mxResources.get("dot"), new GridStyleAction(mxGraphComponent.GRID_STYLE_DOT)));
		backGroundSubmenu.add(editor.bind(mxResources.get("line"), new GridStyleAction(mxGraphComponent.GRID_STYLE_LINE)));
		backGroundSubmenu.add(editor.bind(mxResources.get("cross"), new GridStyleAction(mxGraphComponent.GRID_STYLE_CROSS)));

		diagramMenu.addSeparator();

		layoutSubmenu = (JMenu) diagramMenu.add(new JMenu(mxResources.get("layout")));

		layoutSubmenu.add(editor.graphLayout("verticalHierarchical", true));
		layoutSubmenu.add(editor.graphLayout("horizontalHierarchical", true));

		layoutSubmenu.addSeparator();

		layoutSubmenu.add(editor.graphLayout("verticalPartition", false));
		layoutSubmenu.add(editor.graphLayout("horizontalPartition", false));

		layoutSubmenu.addSeparator();

		layoutSubmenu.add(editor.graphLayout("verticalStack", false));
		layoutSubmenu.add(editor.graphLayout("horizontalStack", false));

		layoutSubmenu.addSeparator();

		layoutSubmenu.add(editor.graphLayout("verticalTree", true));
		layoutSubmenu.add(editor.graphLayout("horizontalTree", true));

		layoutSubmenu.addSeparator();

		layoutSubmenu.add(editor.graphLayout("placeEdgeLabels", false));
		layoutSubmenu.add(editor.graphLayout("parallelEdges", false));

		layoutSubmenu.addSeparator();

		layoutSubmenu.add(editor.graphLayout("organicLayout", true));
		layoutSubmenu.add(editor.graphLayout("circleLayout", true));

		selectionSubmenu = (JMenu) diagramMenu.add(new JMenu(mxResources.get("selection")));

		selectionSubmenu.add(editor.bind(mxResources.get("selectPath"), new SelectShortestPathAction(false)));
		selectionSubmenu.add(editor.bind(mxResources.get("selectDirectedPath"), new SelectShortestPathAction(true)));

		selectionSubmenu.addSeparator();

		selectionSubmenu.add(editor.bind(mxResources.get("selectTree"), new SelectSpanningTreeAction(false)));
		selectionSubmenu.add(editor.bind(mxResources.get("selectDirectedTree"), new SelectSpanningTreeAction(true)));

		diagramMenu.addSeparator();

		styleSubmenu = (JMenu) diagramMenu.add(new JMenu(mxResources.get("stylesheet")));

		styleSubmenu.add(editor.bind(mxResources.get("basicStyle"),
				new StylesheetAction("/com/mxgraph/examples/swing/resources/basic-style.xml")));
		styleSubmenu.add(editor.bind(mxResources.get("defaultStyle"), new StylesheetAction(
				"/com/mxgraph/examples/swing/resources/default-style.xml")));

		// Creates the options menu
		optionMenu = add(new JMenu(mxResources.get("options")));

		displaySubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("display")));
		displaySubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("buffering"), "TripleBuffered", true));

		displaySubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("preferPageSize"), "PreferPageSize", true, new ActionListener()
		{
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e)
			{
				graphComponent.zoomAndCenter();
			}
		}));

		// TODO: This feature is not yet implemented
		//submenu.add(new TogglePropertyItem(graphComponent, mxResources
		//		.get("pageBreaks"), "PageBreaksVisible", true));

		displaySubmenu.addSeparator();

		displaySubmenu.add(editor.bind(mxResources.get("tolerance"), new PromptPropertyAction(graphComponent, "Tolerance")));

		displaySubmenu.add(editor.bind(mxResources.get("dirty"), new ToggleDirtyAction()));

		zoomSubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("zoom")));

		zoomSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("centerZoom"), "CenterZoom", true));
		zoomSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("zoomToSelection"), "KeepSelectionVisibleOnZoom", true));

		zoomSubmenu.addSeparator();

		zoomSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("centerPage"), "CenterPage", true, new ActionListener()
		{
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e)
			{
				if (graphComponent.isPageVisible() && graphComponent.isCenterPage())
				{
					graphComponent.zoomAndCenter();
				}
			}
		}));

		optionMenu.addSeparator();

		dragAndDropSubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("dragAndDrop")));

		dragAndDropSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("dragEnabled"), "DragEnabled"));
		dragAndDropSubmenu.add(new TogglePropertyItem(graph, mxResources.get("dropEnabled"), "DropEnabled"));

		dragAndDropSubmenu.addSeparator();

		dragAndDropSubmenu.add(new TogglePropertyItem(graphComponent.getGraphHandler(), mxResources.get("imagePreview"), "ImagePreview"));

		labelsSubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("labels")));

		labelsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("htmlLabels"), "HtmlLabels", true));
		labelsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("showLabels"), "LabelsVisible", true));

		labelsSubmenu.addSeparator();

		labelsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("moveEdgeLabels"), "EdgeLabelsMovable"));
		labelsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("moveVertexLabels"), "VertexLabelsMovable"));

		labelsSubmenu.addSeparator();

		labelsSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("handleReturn"), "EnterStopsCellEditing"));

		optionMenu.addSeparator();

		connectionsSubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("connections")));

		connectionsSubmenu.add(new TogglePropertyItem(graphComponent, mxResources.get("connectable"), "Connectable"));
		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("connectableEdges"), "ConnectableEdges"));

		connectionsSubmenu.addSeparator();

		connectionsSubmenu.add(new ToggleCreateTargetItem(editor, mxResources.get("createTarget")));
		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("disconnectOnMove"), "DisconnectOnMove"));

		connectionsSubmenu.addSeparator();

		connectionsSubmenu.add(editor.bind(mxResources.get("connectMode"), new ToggleConnectModeAction()));

		connectionsSubmenu = (JMenu) optionMenu.add(new JMenu(mxResources.get("validation")));

		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("allowDanglingEdges"), "AllowDanglingEdges"));
		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("cloneInvalidEdges"), "CloneInvalidEdges"));

		connectionsSubmenu.addSeparator();

		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("allowLoops"), "AllowLoops"));
		connectionsSubmenu.add(new TogglePropertyItem(graph, mxResources.get("multigraph"), "Multigraph"));

		// Creates the window menu
		windowMenu = add(new JMenu(mxResources.get("window")));

		UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < lafs.length; i++)
		{
			final String clazz = lafs[i].getClassName();
			
			windowMenu.add(new AbstractAction(lafs[i].getName())
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 7588919504149148501L;

				public void actionPerformed(ActionEvent e)
				{
					editor.setLookAndFeel(clazz);
				}
			});
		}

		// Creates a developer menu
		generateMenu = add(new JMenu("Generate"));
		generateMenu.add(editor.bind("Null Graph", new InsertGraph(GraphType.NULL, aGraph)));
		generateMenu.add(editor.bind("Complete Graph", new InsertGraph(GraphType.COMPLETE, aGraph)));
		generateMenu.add(editor.bind("Grid", new InsertGraph(GraphType.GRID, aGraph)));
		generateMenu.add(editor.bind("Bipartite", new InsertGraph(GraphType.BIPARTITE, aGraph)));
		generateMenu.add(editor.bind("Complete Bipartite", new InsertGraph(GraphType.COMPLETE_BIPARTITE, aGraph)));
		generateMenu.add(editor.bind("Knight's Graph", new InsertGraph(GraphType.KNIGHT, aGraph)));
		generateMenu.add(editor.bind("King's Graph", new InsertGraph(GraphType.KING, aGraph)));
		generateMenu.add(editor.bind("Petersen", new InsertGraph(GraphType.PETERSEN, aGraph)));
		generateMenu.add(editor.bind("Path", new InsertGraph(GraphType.PATH, aGraph)));
		generateMenu.add(editor.bind("Star", new InsertGraph(GraphType.STAR, aGraph)));
		generateMenu.add(editor.bind("Wheel", new InsertGraph(GraphType.WHEEL, aGraph)));
		generateMenu.add(editor.bind("Friendship Windmill", new InsertGraph(GraphType.FRIENDSHIP_WINDMILL, aGraph)));
		generateMenu.add(editor.bind("Full Windmill", new InsertGraph(GraphType.FULL_WINDMILL, aGraph)));
		generateMenu.add(editor.bind("Knight's Tour", new InsertGraph(GraphType.KNIGHT_TOUR, aGraph)));
		generateMenu.addSeparator();
		generateMenu.add(editor.bind("Simple Random", new InsertGraph(GraphType.SIMPLE_RANDOM, aGraph)));
		generateMenu.add(editor.bind("Simple Random Tree", new InsertGraph(GraphType.SIMPLE_RANDOM_TREE, aGraph)));
		generateMenu.addSeparator();
		generateMenu.add(editor.bind("Reset Style", new InsertGraph(GraphType.RESET_STYLE, aGraph)));

		analyzeMenu = add(new JMenu("Analyze"));
		analyzeMenu.add(editor.bind("Is Connected", new AnalyzeGraph(AnalyzeType.IS_CONNECTED, aGraph)));
		analyzeMenu.add(editor.bind("Is Simple", new AnalyzeGraph(AnalyzeType.IS_SIMPLE, aGraph)));
		analyzeMenu.add(editor.bind("Is Directed Cyclic", new AnalyzeGraph(AnalyzeType.IS_CYCLIC_DIRECTED, aGraph)));
		analyzeMenu.add(editor.bind("Is Undirected Cyclic", new AnalyzeGraph(AnalyzeType.IS_CYCLIC_UNDIRECTED, aGraph)));
		analyzeMenu.add(editor.bind("BFS Directed", new InsertGraph(GraphType.BFS_DIR, aGraph)));
		analyzeMenu.add(editor.bind("BFS Undirected", new InsertGraph(GraphType.BFS_UNDIR, aGraph)));
		analyzeMenu.add(editor.bind("DFS Directed", new InsertGraph(GraphType.DFS_DIR, aGraph)));
		analyzeMenu.add(editor.bind("DFS Undirected", new InsertGraph(GraphType.DFS_UNDIR, aGraph)));
		analyzeMenu.add(editor.bind("Complementary", new AnalyzeGraph(AnalyzeType.COMPLEMENTARY, aGraph)));
		analyzeMenu.add(editor.bind("Regularity", new AnalyzeGraph(AnalyzeType.REGULARITY, aGraph)));
		analyzeMenu.add(editor.bind("Dijkstra", new InsertGraph(GraphType.DIJKSTRA, aGraph)));
		analyzeMenu.add(editor.bind("Bellman-Ford", new InsertGraph(GraphType.BELLMAN_FORD, aGraph)));
		analyzeMenu.add(editor.bind("Floyd-Roy-Warshall", new AnalyzeGraph(AnalyzeType.FLOYD_ROY_WARSHALL, aGraph)));
		analyzeMenu.add(editor.bind("Get Components", new AnalyzeGraph(AnalyzeType.COMPONENTS, aGraph)));
		analyzeMenu.add(editor.bind("Make Connected", new AnalyzeGraph(AnalyzeType.MAKE_CONNECTED, aGraph)));
		analyzeMenu.add(editor.bind("Make Simple", new AnalyzeGraph(AnalyzeType.MAKE_SIMPLE, aGraph)));
		analyzeMenu.add(editor.bind("Is Tree", new AnalyzeGraph(AnalyzeType.IS_TREE, aGraph)));
		analyzeMenu.add(editor.bind("One Spanning Tree", new AnalyzeGraph(AnalyzeType.ONE_SPANNING_TREE, aGraph)));
		analyzeMenu.add(editor.bind("Make tree directed", new InsertGraph(GraphType.MAKE_TREE_DIRECTED, aGraph)));
		analyzeMenu.add(editor.bind("Is directed", new AnalyzeGraph(AnalyzeType.IS_DIRECTED, aGraph)));
		analyzeMenu.add(editor.bind("Indegree", new InsertGraph(GraphType.INDEGREE, aGraph)));
		analyzeMenu.add(editor.bind("Outdegree", new InsertGraph(GraphType.OUTDEGREE, aGraph)));
		analyzeMenu.add(editor.bind("Is cut vertex", new InsertGraph(GraphType.IS_CUT_VERTEX, aGraph)));
		analyzeMenu.add(editor.bind("Get cut vertices", new AnalyzeGraph(AnalyzeType.GET_CUT_VERTEXES, aGraph)));
		analyzeMenu.add(editor.bind("Get cut edges", new AnalyzeGraph(AnalyzeType.GET_CUT_EDGES, aGraph)));
		analyzeMenu.add(editor.bind("Get sources", new AnalyzeGraph(AnalyzeType.GET_SOURCES, aGraph)));
		analyzeMenu.add(editor.bind("Get sinks", new AnalyzeGraph(AnalyzeType.GET_SINKS, aGraph)));
		analyzeMenu.add(editor.bind("Is biconnected", new AnalyzeGraph(AnalyzeType.IS_BICONNECTED, aGraph)));

		// Creates the help menu
		helpMenu = add(new JMenu(mxResources.get("help")));

		JMenuItem item = helpMenu.add(new JMenuItem(mxResources.get("aboutGraphEditor")));
		item.addActionListener(new ActionListener()
		{
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e)
			{
				editor.about();
			}
		});
	}
	
	public static void clearPalette(){
		if (editor != null)
		{
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
			{
				mxGraph graph = editor.getGraphComponent().getGraph();

				// Check modified flag and display save dialog
				mxCell root = new mxCell();
				root.insert(new mxCell());
				graph.getModel().setRoot(root);

				editor.setModified(false);
				editor.setCurrentFile(null);
				editor.getGraphComponent().zoomAndCenter();
			}
		}
		// TODO Auto-generated method stub
		editor.removePalette();
	}
	
	public static void openNewFile() {
		
	}
	
	public static void insertFamilyPalette(){
		familiesPalette = editor.insertPalette("Families");
		selectedPalette = Palette.FAMILIES.toString();
		saveAction.paletteTtype = saveAsAction.paletteTtype = openAction.paletteType = Palette.FAMILIES.toNumber(); 
		
		familiesPalette
				.addTemplate(
						"Container",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/swimlane.png")),
						"swimlane", 280, 280, "Container");
		familiesPalette.addTemplate(
				"Icon",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/rounded.png")),
				"icon;image=/com/mxgraph/examples/swing/images/wrench.png",
				70, 70, "Icon");
		familiesPalette.addTemplate(
				"Label",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/rounded.png")),
				"label;image=/com/mxgraph/examples/swing/images/gear.png",
				130, 50, "Label");
	}
	/****************Changed by bdlions***********************/
	public static void insertHomePalette(){
		homePalette = editor.insertPalette("Home");
		selectedPalette = Palette.HOME.toString();
		saveAction.paletteTtype = saveAsAction.paletteTtype = openAction.paletteType = Palette.HOME.toNumber(); 
		
		homePalette
				.addTemplate(
						"Box",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/box.png")),
						"image;image=/com/mxgraph/examples/swing/images/box.png",
						50, 50, "Box");
		homePalette
				.addTemplate(
						"Cube",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/cube_green.png")),
						"image;image=/com/mxgraph/examples/swing/images/cube_green.png",
						50, 50, "Cube");
		homePalette
				.addTemplate(
						"User",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/dude3.png")),
						"roundImage;image=/com/mxgraph/examples/swing/images/dude3.png",
						50, 50, "User");
	}
	public static void insertProcessPalette(){
		processPalette = editor.insertPalette("Process");
		selectedPalette = Palette.PROCESS.toString();
		saveAction.paletteTtype = saveAsAction.paletteTtype = openAction.paletteType = Palette.PROCESS.toNumber(); 
		
		processPalette
				.addTemplate(
						"Cancel",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/cancel_end.png")),
						"roundImage;image=/com/mxgraph/examples/swing/images/cancel_end.png",
						80, 80, "Cancel");
		processPalette
				.addTemplate(
						"Error",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/error.png")),
						"roundImage;image=/com/mxgraph/examples/swing/images/error.png",
						80, 80, "Error");
		processPalette
				.addTemplate(
						"Event",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/event.png")),
						"roundImage;image=/com/mxgraph/examples/swing/images/event.png",
						80, 80, "Event");

	}
	/****************Changed by bdlions***********************/
	/**
	 * Adds menu items to the given shape menu. This is factored out because
	 * the shape menu appears in the menubar and also in the popupmenu.
	 */
	public static void populateShapeMenu(JMenu menu, BasicGraphEditor editor)
	{
		shape_menu_item_home = menu.add(editor.bind(mxResources.get("home"), mxGraphActions.getHomeAction(), "/com/mxgraph/examples/swing/images/house.gif"));

		menu.addSeparator();

		shape_menu_item_exitGroup = menu.add(editor.bind(mxResources.get("exitGroup"), mxGraphActions.getExitGroupAction(), "/com/mxgraph/examples/swing/images/up.gif"));
		shape_menu_item_enterGroup = menu.add(editor.bind(mxResources.get("enterGroup"), mxGraphActions.getEnterGroupAction(),
				"/com/mxgraph/examples/swing/images/down.gif"));

		menu.addSeparator();

		shape_menu_item_group = menu.add(editor.bind(mxResources.get("group"), mxGraphActions.getGroupAction(), "/com/mxgraph/examples/swing/images/group.gif"));
		shape_menu_item_unGroup = menu.add(editor.bind(mxResources.get("ungroup"), mxGraphActions.getUngroupAction(),
				"/com/mxgraph/examples/swing/images/ungroup.gif"));

		menu.addSeparator();

		shape_menu_item_removeFromGroup = menu.add(editor.bind(mxResources.get("removeFromGroup"), mxGraphActions.getRemoveFromParentAction()));

		shape_menu_item_updateGroupBounds = menu.add(editor.bind(mxResources.get("updateGroupBounds"), mxGraphActions.getUpdateGroupBoundsAction()));

		menu.addSeparator();

		shape_menu_item_collapse = menu.add(editor.bind(mxResources.get("collapse"), mxGraphActions.getCollapseAction(),
				"/com/mxgraph/examples/swing/images/collapse.gif"));
		shape_menu_item_expand = menu.add(editor.bind(mxResources.get("expand"), mxGraphActions.getExpandAction(), "/com/mxgraph/examples/swing/images/expand.gif"));

		menu.addSeparator();

		shape_menu_item_toBack = menu.add(editor.bind(mxResources.get("toBack"), mxGraphActions.getToBackAction(), "/com/mxgraph/examples/swing/images/toback.gif"));
		shape_menu_item_toFront = menu.add(editor.bind(mxResources.get("toFront"), mxGraphActions.getToFrontAction(),
				"/com/mxgraph/examples/swing/images/tofront.gif"));

		menu.addSeparator();

		JMenu submenu = (JMenu) menu.add(new JMenu(mxResources.get("align")));

		submenu.add(editor.bind(mxResources.get("left"), new AlignCellsAction(mxConstants.ALIGN_LEFT),
				"/com/mxgraph/examples/swing/images/alignleft.gif"));
		submenu.add(editor.bind(mxResources.get("center"), new AlignCellsAction(mxConstants.ALIGN_CENTER),
				"/com/mxgraph/examples/swing/images/aligncenter.gif"));
		submenu.add(editor.bind(mxResources.get("right"), new AlignCellsAction(mxConstants.ALIGN_RIGHT),
				"/com/mxgraph/examples/swing/images/alignright.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("top"), new AlignCellsAction(mxConstants.ALIGN_TOP),
				"/com/mxgraph/examples/swing/images/aligntop.gif"));
		submenu.add(editor.bind(mxResources.get("middle"), new AlignCellsAction(mxConstants.ALIGN_MIDDLE),
				"/com/mxgraph/examples/swing/images/alignmiddle.gif"));
		submenu.add(editor.bind(mxResources.get("bottom"), new AlignCellsAction(mxConstants.ALIGN_BOTTOM),
				"/com/mxgraph/examples/swing/images/alignbottom.gif"));

		menu.addSeparator();

		shape_menu_item_autoSize = menu.add(editor.bind(mxResources.get("autosize"), new AutosizeAction()));

	}

	/**
	 * Adds menu items to the given format menu. This is factored out because
	 * the format menu appears in the menubar and also in the popupmenu.
	 */
	public static void populateFormatMenu(JMenu menu, BasicGraphEditor editor)
	{
		format_menu_item_background = (JMenu) menu.add(new JMenu(mxResources.get("background")));

		format_background_menu_item_fill_color = format_menu_item_background.add(editor.bind(mxResources.get("fillcolor"), new ColorAction("Fillcolor", mxConstants.STYLE_FILLCOLOR),
				"/com/mxgraph/examples/swing/images/fillcolor.gif"));
		format_background_menu_item_gradient = format_menu_item_background.add(editor.bind(mxResources.get("gradient"), new ColorAction("Gradient", mxConstants.STYLE_GRADIENTCOLOR)));

		format_menu_item_background.addSeparator();

		format_background_menu_item_image = format_menu_item_background.add(editor.bind(mxResources.get("image"), new PromptValueAction(mxConstants.STYLE_IMAGE, "Image")));
		format_background_menu_item_shadow = format_menu_item_background.add(editor.bind(mxResources.get("shadow"), new ToggleAction(mxConstants.STYLE_SHADOW)));

		format_menu_item_background.addSeparator();

		format_background_menu_item_opacity = format_menu_item_background.add(editor.bind(mxResources.get("opacity"), new PromptValueAction(mxConstants.STYLE_OPACITY, "Opacity (0-100)")));
		
		format_menu_item_label = (JMenu) menu.add(new JMenu(mxResources.get("label")));

		format_label_menu_item_font_color = format_menu_item_label.add(editor.bind(mxResources.get("fontcolor"), new ColorAction("Fontcolor", mxConstants.STYLE_FONTCOLOR),
				"/com/mxgraph/examples/swing/images/fontcolor.gif"));

		format_menu_item_label.addSeparator();

		format_label_menu_item_label_fill = format_menu_item_label.add(editor.bind(mxResources.get("labelFill"), new ColorAction("Label Fill", mxConstants.STYLE_LABEL_BACKGROUNDCOLOR)));
		format_label_menu_item_label_border = format_menu_item_label.add(editor.bind(mxResources.get("labelBorder"), new ColorAction("Label Border", mxConstants.STYLE_LABEL_BORDERCOLOR)));

		format_menu_item_label.addSeparator();

		format_label_menu_item_rotate_label = format_menu_item_label.add(editor.bind(mxResources.get("rotateLabel"), new ToggleAction(mxConstants.STYLE_HORIZONTAL, true)));

		format_label_menu_item_text_opacity = format_menu_item_label.add(editor.bind(mxResources.get("textOpacity"), new PromptValueAction(mxConstants.STYLE_TEXT_OPACITY, "Opacity (0-100)")));

		format_menu_item_label.addSeparator();

		format_label_menu_item_position = (JMenu) format_menu_item_label.add(new JMenu(mxResources.get("position")));

		format_label_menu_item_position.add(editor.bind(mxResources.get("top"), new SetLabelPositionAction(mxConstants.ALIGN_TOP, mxConstants.ALIGN_BOTTOM)));
		format_label_menu_item_position.add(editor.bind(mxResources.get("middle"),
				new SetLabelPositionAction(mxConstants.ALIGN_MIDDLE, mxConstants.ALIGN_MIDDLE)));
		format_label_menu_item_position.add(editor.bind(mxResources.get("bottom"), new SetLabelPositionAction(mxConstants.ALIGN_BOTTOM, mxConstants.ALIGN_TOP)));

		format_label_menu_item_position.addSeparator();

		format_label_menu_item_position.add(editor.bind(mxResources.get("left"), new SetLabelPositionAction(mxConstants.ALIGN_LEFT, mxConstants.ALIGN_RIGHT)));
		format_label_menu_item_position.add(editor.bind(mxResources.get("center"),
				new SetLabelPositionAction(mxConstants.ALIGN_CENTER, mxConstants.ALIGN_CENTER)));
		format_label_menu_item_position.add(editor.bind(mxResources.get("right"), new SetLabelPositionAction(mxConstants.ALIGN_RIGHT, mxConstants.ALIGN_LEFT)));

		format_menu_item_label.addSeparator();

		format_label_menu_item_word_wrap = format_menu_item_label.add(editor.bind(mxResources.get("wordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, "wrap")));
		format_label_menu_item_no_word_wrap = format_menu_item_label.add(editor.bind(mxResources.get("noWordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, null)));

		format_menu_item_label.addSeparator();

		format_label_menu_item_hide = format_menu_item_label.add(editor.bind(mxResources.get("hide"), new ToggleAction(mxConstants.STYLE_NOLABEL)));

		menu.addSeparator();

		format_menu_item_line = (JMenu) menu.add(new JMenu(mxResources.get("line")));

		format_line_menu_item_line_color = format_menu_item_line.add(editor.bind(mxResources.get("linecolor"), new ColorAction("Linecolor", mxConstants.STYLE_STROKECOLOR),
				"/com/mxgraph/examples/swing/images/linecolor.gif"));

		format_menu_item_line.addSeparator();

		format_line_menu_item_orthogonal = format_menu_item_line.add(editor.bind(mxResources.get("orthogonal"), new ToggleAction(mxConstants.STYLE_ORTHOGONAL)));
		format_line_menu_item_dashed = format_menu_item_line.add(editor.bind(mxResources.get("dashed"), new ToggleAction(mxConstants.STYLE_DASHED)));

		format_menu_item_line.addSeparator();

		format_line_menu_item_line_width = format_menu_item_line.add(editor.bind(mxResources.get("linewidth"), new PromptValueAction(mxConstants.STYLE_STROKEWIDTH, "Linewidth")));

		format_menu_item_connector = (JMenu) menu.add(new JMenu(mxResources.get("connector")));

		format_connector_menu_item_straight = format_menu_item_connector.add(editor.bind(mxResources.get("straight"), new SetStyleAction("straight"),
				"/com/mxgraph/examples/swing/images/straight.gif"));

		format_connector_menu_item_horizontal = format_menu_item_connector.add(editor.bind(mxResources.get("horizontal"), new SetStyleAction(""), "/com/mxgraph/examples/swing/images/connect.gif"));
		format_connector_menu_item_vertical = format_menu_item_connector.add(editor.bind(mxResources.get("vertical"), new SetStyleAction("vertical"),
				"/com/mxgraph/examples/swing/images/vertical.gif"));

		format_menu_item_connector.addSeparator();

		format_connector_menu_item_entity_relation = format_menu_item_connector.add(editor.bind(mxResources.get("entityRelation"), new SetStyleAction("edgeStyle=mxEdgeStyle.EntityRelation"),
				"/com/mxgraph/examples/swing/images/entity.gif"));
		format_connector_menu_item_arrow = format_menu_item_connector.add(editor.bind(mxResources.get("arrow"), new SetStyleAction("arrow"), "/com/mxgraph/examples/swing/images/arrow.gif"));

		format_menu_item_connector.addSeparator();

		format_connector_menu_item_plain = format_menu_item_connector.add(editor.bind(mxResources.get("plain"), new ToggleAction(mxConstants.STYLE_NOEDGESTYLE)));

		menu.addSeparator();

		format_menu_item_line_start = (JMenu) menu.add(new JMenu(mxResources.get("linestart")));

		format_line_start_menu_item_open = format_menu_item_line_start.add(editor.bind(mxResources.get("open"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OPEN),
				"/com/mxgraph/examples/swing/images/open_start.gif"));
		format_line_start_menu_item_classic = format_menu_item_line_start.add(editor.bind(mxResources.get("classic"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_CLASSIC),
				"/com/mxgraph/examples/swing/images/classic_start.gif"));
		format_line_start_menu_item_block = format_menu_item_line_start.add(editor.bind(mxResources.get("block"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_BLOCK),
				"/com/mxgraph/examples/swing/images/block_start.gif"));

		format_menu_item_line_start.addSeparator();

		format_line_start_menu_item_diamond = format_menu_item_line_start.add(editor.bind(mxResources.get("diamond"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_DIAMOND),
				"/com/mxgraph/examples/swing/images/diamond_start.gif"));
		format_line_start_menu_item_oval = format_menu_item_line_start.add(editor.bind(mxResources.get("oval"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OVAL),
				"/com/mxgraph/examples/swing/images/oval_start.gif"));

		format_menu_item_line_start.addSeparator();

		format_line_start_menu_item_none = format_menu_item_line_start.add(editor.bind(mxResources.get("none"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.NONE)));
		format_line_start_menu_item_size = format_menu_item_line_start.add(editor.bind(mxResources.get("size"), new PromptValueAction(mxConstants.STYLE_STARTSIZE, "Linestart Size")));

		format_menu_item_lineed = (JMenu) menu.add(new JMenu(mxResources.get("lineend")));

		format_linned_menu_item_open = format_menu_item_lineed.add(editor.bind(mxResources.get("open"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN),
				"/com/mxgraph/examples/swing/images/open_end.gif"));
		format_linned_menu_item_classic = format_menu_item_lineed.add(editor.bind(mxResources.get("classic"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC),
				"/com/mxgraph/examples/swing/images/classic_end.gif"));
		format_linned_menu_item_block = format_menu_item_lineed.add(editor.bind(mxResources.get("block"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_BLOCK),
				"/com/mxgraph/examples/swing/images/block_end.gif"));

		format_menu_item_lineed.addSeparator();

		format_linned_menu_item_diamond = format_menu_item_lineed.add(editor.bind(mxResources.get("diamond"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_DIAMOND),
				"/com/mxgraph/examples/swing/images/diamond_end.gif"));
		format_linned_menu_item_oval = format_menu_item_lineed.add(editor.bind(mxResources.get("oval"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OVAL),
				"/com/mxgraph/examples/swing/images/oval_end.gif"));

		format_menu_item_lineed.addSeparator();

		format_linned_menu_item_none = format_menu_item_lineed.add(editor.bind(mxResources.get("none"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.NONE)));
		format_linned_menu_item_size = format_menu_item_lineed.add(editor.bind(mxResources.get("size"), new PromptValueAction(mxConstants.STYLE_ENDSIZE, "Lineend Size")));

		menu.addSeparator();

		format_menu_item_alignment = (JMenu) menu.add(new JMenu(mxResources.get("alignment")));

		format_alignment_menu_item_left = format_menu_item_alignment.add(editor.bind(mxResources.get("left"), new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT),
				"/com/mxgraph/examples/swing/images/left.gif"));
		format_alignment_menu_item_center = format_menu_item_alignment.add(editor.bind(mxResources.get("center"), new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER),
				"/com/mxgraph/examples/swing/images/center.gif"));
		format_alignment_menu_item_right = format_menu_item_alignment.add(editor.bind(mxResources.get("right"), new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_RIGHT),
				"/com/mxgraph/examples/swing/images/right.gif"));

		format_menu_item_alignment.addSeparator();

		format_alignment_menu_item_top = format_menu_item_alignment.add(editor.bind(mxResources.get("top"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_TOP),
				"/com/mxgraph/examples/swing/images/top.gif"));
		format_alignment_menu_item_middle = format_menu_item_alignment.add(editor.bind(mxResources.get("middle"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE),
				"/com/mxgraph/examples/swing/images/middle.gif"));
		format_alignment_menu_item_bottom = format_menu_item_alignment.add(editor.bind(mxResources.get("bottom"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM),
				"/com/mxgraph/examples/swing/images/bottom.gif"));

		format_menu_item_spacing = (JMenu) menu.add(new JMenu(mxResources.get("spacing")));

		format_spacing_menu_item_top = format_menu_item_spacing.add(editor.bind(mxResources.get("top"), new PromptValueAction(mxConstants.STYLE_SPACING_TOP, "Top Spacing")));
		format_spacing_menu_item_right = format_menu_item_spacing.add(editor.bind(mxResources.get("right"), new PromptValueAction(mxConstants.STYLE_SPACING_RIGHT, "Right Spacing")));
		format_spacing_menu_item_bottom = format_menu_item_spacing.add(editor.bind(mxResources.get("bottom"), new PromptValueAction(mxConstants.STYLE_SPACING_BOTTOM, "Bottom Spacing")));
		format_spacing_menu_item_left = format_menu_item_spacing.add(editor.bind(mxResources.get("left"), new PromptValueAction(mxConstants.STYLE_SPACING_LEFT, "Left Spacing")));

		format_menu_item_spacing.addSeparator();

		format_spacing_menu_item_global = format_menu_item_spacing.add(editor.bind(mxResources.get("global"), new PromptValueAction(mxConstants.STYLE_SPACING, "Spacing")));

		format_menu_item_spacing.addSeparator();

		format_spacing_menu_item_source_spacing = format_menu_item_spacing.add(editor.bind(mxResources.get("sourceSpacing"), new PromptValueAction(mxConstants.STYLE_SOURCE_PERIMETER_SPACING,
				mxResources.get("sourceSpacing"))));
		format_spacing_menu_item_target_spacing = format_menu_item_spacing.add(editor.bind(mxResources.get("targetSpacing"), new PromptValueAction(mxConstants.STYLE_TARGET_PERIMETER_SPACING,
				mxResources.get("targetSpacing"))));

		format_menu_item_spacing.addSeparator();

		format_spacing_menu_item_perimeter = format_menu_item_spacing.add(editor.bind(mxResources.get("perimeter"), new PromptValueAction(mxConstants.STYLE_PERIMETER_SPACING,
				"Perimeter Spacing")));

		format_menu_item_direction = (JMenu) menu.add(new JMenu(mxResources.get("direction")));

	    format_direction_menu_item_north = format_menu_item_direction.add(editor.bind(mxResources.get("north"), new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_NORTH)));
	    format_direction_menu_item_east = format_menu_item_direction.add(editor.bind(mxResources.get("east"), new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_EAST)));
	    format_direction_menu_item_south = format_menu_item_direction.add(editor.bind(mxResources.get("south"), new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_SOUTH)));
	    format_direction_menu_item_west = format_menu_item_direction.add(editor.bind(mxResources.get("west"), new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_WEST)));

		format_menu_item_direction.addSeparator();

		format_direction_menu_item_rotation = format_menu_item_direction.add(editor.bind(mxResources.get("rotation"), new PromptValueAction(mxConstants.STYLE_ROTATION, "Rotation (0-360)")));

		menu.addSeparator();

		format_menu_item_rounded = menu.add(editor.bind(mxResources.get("rounded"), new ToggleAction(mxConstants.STYLE_ROUNDED)));

		format_menu_item_style = menu.add(editor.bind(mxResources.get("style"), new StyleAction()));
		/************Changed by  bdlions***************************/
	}

	/**************Changed by  bdlions********************/
	public void resetEditorMenu(){
		fileMenu.setText(mxResources.get("file"));
		file_menu_item_import.setText(mxResources.get("importStencil"));
		file_menu_item_new.setText(mxResources.get("new"));
		file_menu_item_open.setText(mxResources.get("openFile"));
		file_menu_item_save.setText(mxResources.get("save"));
		file_menu_item_save_as.setText(mxResources.get("saveAs"));
		file_menu_item_page_setup.setText(mxResources.get("pageSetup"));
		file_menu_item_print.setText(mxResources.get("print"));
		file_menu_item_exit.setText(mxResources.get("exit"));
		
		
		edit_menu_item_copy.setText(mxResources.get("copy"));
		edit_menu_item_cut.setText(mxResources.get("cut"));
		edit_menu_item_delete.setText(mxResources.get("delete"));
		edit_menu_item_paste.setText(mxResources.get("paste"));
		edit_menu_item_redo.setText(mxResources.get("redo"));
		edit_menu_item_select_all.setText(mxResources.get("selectAll"));
		edit_menu_item_select_none.setText(mxResources.get("selectNone"));
		edit_menu_item_undo.setText(mxResources.get("undo"));
		edit_menu_item_warning.setText(mxResources.get("warning"));
		
		format_background_menu_item_fill_color.setText(mxResources.get("fillcolor"));
		format_background_menu_item_gradient.setText(mxResources.get("gradient"));
		format_background_menu_item_image.setText(mxResources.get("image"));
		format_background_menu_item_shadow.setText(mxResources.get("shadow"));
		format_background_menu_item_opacity.setText(mxResources.get("opacity"));
		
		format_menu_item_label.setText(mxResources.get("label"));
		format_label_menu_item_font_color.setText(mxResources.get("fontColor"));
		format_label_menu_item_label_fill.setText(mxResources.get("labelFill"));
		format_label_menu_item_label_border.setText(mxResources.get("labelBorder"));
		format_label_menu_item_rotate_label.setText(mxResources.get("rotateLabel"));
		format_label_menu_item_text_opacity.setText(mxResources.get("textOpacity"));
		format_label_menu_item_position.setText(mxResources.get("position"));
		format_label_menu_item_word_wrap.setText(mxResources.get("wordWrap"));
		format_label_menu_item_no_word_wrap.setText(mxResources.get("wordNoWrap"));
		format_label_menu_item_hide.setText(mxResources.get("hide"));
		
		format_menu_item_line.setText(mxResources.get("line"));
		format_line_menu_item_line_color.setText(mxResources.get("lineColor"));
		format_line_menu_item_orthogonal.setText(mxResources.get("orthogonal"));
		format_line_menu_item_dashed.setText(mxResources.get("dashed"));
		format_line_menu_item_line_width.setText(mxResources.get("lineWidth"));
		
		format_menu_item_connector.setText(mxResources.get("connector"));
		format_connector_menu_item_straight.setText(mxResources.get("straight"));
		format_connector_menu_item_horizontal.setText(mxResources.get("horizontal"));
		format_connector_menu_item_vertical.setText(mxResources.get("vertical"));
		format_connector_menu_item_entity_relation.setText(mxResources.get("entityRelation"));
		format_connector_menu_item_arrow.setText(mxResources.get("arrow"));
		format_connector_menu_item_plain.setText(mxResources.get("plain"));
		
		format_menu_item_line_start.setText(mxResources.get("lineStart"));
		format_line_start_menu_item_open.setText(mxResources.get("open"));
		format_line_start_menu_item_classic.setText(mxResources.get("classic"));
		format_line_start_menu_item_block.setText(mxResources.get("block"));
		format_line_start_menu_item_diamond.setText(mxResources.get("diamond"));
		format_line_start_menu_item_oval.setText(mxResources.get("oval"));
		format_line_start_menu_item_none.setText(mxResources.get("none"));
		format_line_start_menu_item_size.setText(mxResources.get("size"));
		
		format_menu_item_lineed.setText(mxResources.get("linned"));
		format_linned_menu_item_open.setText(mxResources.get("open"));
		format_linned_menu_item_classic.setText(mxResources.get("classic"));
		format_linned_menu_item_block.setText(mxResources.get("block"));
		format_linned_menu_item_diamond.setText(mxResources.get("diamond"));
		format_linned_menu_item_oval.setText(mxResources.get("oval"));
		format_linned_menu_item_none.setText(mxResources.get("none"));
		format_linned_menu_item_size.setText(mxResources.get("size"));
		
		format_menu_item_alignment.setText(mxResources.get("alignment"));
		format_alignment_menu_item_left.setText(mxResources.get("left"));
		format_alignment_menu_item_center.setText(mxResources.get("center"));
		format_alignment_menu_item_right.setText(mxResources.get("right"));
		format_alignment_menu_item_top.setText(mxResources.get("top"));
		format_alignment_menu_item_middle.setText(mxResources.get("middle"));
		format_alignment_menu_item_bottom.setText(mxResources.get("bottom"));
		
		format_menu_item_spacing.setText(mxResources.get("spacing"));
		format_spacing_menu_item_top.setText(mxResources.get("top"));
		format_spacing_menu_item_right.setText(mxResources.get("right"));
		format_spacing_menu_item_bottom.setText(mxResources.get("bottom"));
		format_spacing_menu_item_left.setText(mxResources.get("left"));
		format_spacing_menu_item_global.setText(mxResources.get("global"));
		format_spacing_menu_item_source_spacing.setText(mxResources.get("sourceSpacing"));
		format_spacing_menu_item_target_spacing.setText(mxResources.get("targetSpaing"));
		format_spacing_menu_item_perimeter.setText(mxResources.get("perimeter"));
		
		format_menu_item_direction.setText(mxResources.get("direction"));
		format_direction_menu_item_north.setText(mxResources.get("north"));
		format_direction_menu_item_east.setText(mxResources.get("east"));
		format_direction_menu_item_south.setText(mxResources.get("south"));
		format_direction_menu_item_west.setText(mxResources.get("west"));
		format_direction_menu_item_rotation.setText(mxResources.get("rotation"));
		
		format_menu_item_rounded.setText(mxResources.get("fillcolor"));
		format_menu_item_style.setText(mxResources.get("fillcolor"));
		
		view_menu_item_antialias.setText(mxResources.get("antialias"));
		view_menu_item_page_layout.setText(mxResources.get("pageLayout"));
		view_menu_item_actual_size.setText(mxResources.get("actualSize"));
		view_menu_item_custom.setText(mxResources.get("custom"));
		view_menu_item_grid.setText(mxResources.get("grid"));
		view_menu_item_page.setText(mxResources.get("page"));
		view_menu_item_rulers.setText(mxResources.get("rulers"));
		view_menu_item_width.setText(mxResources.get("width"));
		view_menu_item_zoom.setText(mxResources.get("zoom"));
		view_menu_item_zoomin.setText(mxResources.get("zoomIn"));
		view_menu_item_zoomout.setText(mxResources.get("zoomOut"));
		
		editMenu.setText(mxResources.get("edit"));
		viewMenu.setText(mxResources.get("view"));
		formatMenu.setText(mxResources.get("format"));
		shapeMenu.setText(mxResources.get("shape"));
		
		shape_menu_item_exitGroup.setText(mxResources.get("exitGroup"));
		shape_menu_item_enterGroup.setText(mxResources.get("enterGroup"));
		shape_menu_item_home.setText(mxResources.get("home"));
		shape_menu_item_group.setText(mxResources.get("group"));
		shape_menu_item_unGroup.setText(mxResources.get("unGroup"));
		shape_menu_item_removeFromGroup.setText(mxResources.get("removeFromGroup"));
		shape_menu_item_updateGroupBounds.setText(mxResources.get("updateGroupBounds"));
		shape_menu_item_collapse.setText(mxResources.get("collapse"));
		shape_menu_item_expand.setText(mxResources.get("expand"));
		shape_menu_item_toBack.setText(mxResources.get("toBack"));
		shape_menu_item_toFront.setText(mxResources.get("toFront"));
		shape_menu_item_autoSize.setText(mxResources.get("autoSize"));
		
		diagramMenu.setText(mxResources.get("diagram"));
		backGroundSubmenu.setText(mxResources.get("background"));
		layoutSubmenu.setText(mxResources.get("layout"));
		selectionSubmenu.setText(mxResources.get("selection"));
		styleSubmenu.setText(mxResources.get("style"));
		displaySubmenu.setText(mxResources.get("display"));
		zoomSubmenu.setText(mxResources.get("zoom"));
		dragAndDropSubmenu.setText(mxResources.get("dragAndDrop"));
		labelsSubmenu.setText(mxResources.get("label"));
		connectionsSubmenu.setText(mxResources.get("connections"));
		windowMenu.setText(mxResources.get("window"));
		analyzeMenu.setText(mxResources.get("analyze"));
		generateMenu.setText(mxResources.get("generate"));
		helpMenu.setText(mxResources.get("help"));
		
	}
	/**************Changed by  bdlions********************/

	/**
	 *
	 */
	public static class InsertGraph extends AbstractAction
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4010463992665008365L;

		/**
		 * 
		 */
		protected GraphType graphType;

		protected mxAnalysisGraph aGraph;

		/**
		 * @param aGraph 
		 * 
		 */
		public InsertGraph(GraphType tree, mxAnalysisGraph aGraph)
		{
			this.graphType = tree;
			this.aGraph = aGraph;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e.getSource();
				mxGraph graph = graphComponent.getGraph();

				// dialog = new FactoryConfigDialog();
				String dialogText = "";
				if (graphType == GraphType.NULL)
					dialogText = "Configure null graph";
				else if (graphType == GraphType.COMPLETE)
					dialogText = "Configure complete graph";
				else if (graphType == GraphType.NREGULAR)
					dialogText = "Configure n-regular graph";
				else if (graphType == GraphType.GRID)
					dialogText = "Configure grid graph";
				else if (graphType == GraphType.BIPARTITE)
					dialogText = "Configure bipartite graph";
				else if (graphType == GraphType.COMPLETE_BIPARTITE)
					dialogText = "Configure complete bipartite graph";
				else if (graphType == GraphType.BFS_DIR)
					dialogText = "Configure BFS algorithm";
				else if (graphType == GraphType.BFS_UNDIR)
					dialogText = "Configure BFS algorithm";
				else if (graphType == GraphType.DFS_DIR)
					dialogText = "Configure DFS algorithm";
				else if (graphType == GraphType.DFS_UNDIR)
					dialogText = "Configure DFS algorithm";
				else if (graphType == GraphType.DIJKSTRA)
					dialogText = "Configure Dijkstra's algorithm";
				else if (graphType == GraphType.BELLMAN_FORD)
					dialogText = "Configure Bellman-Ford algorithm";
				else if (graphType == GraphType.MAKE_TREE_DIRECTED)
					dialogText = "Configure make tree directed algorithm";
				else if (graphType == GraphType.KNIGHT_TOUR)
					dialogText = "Configure knight's tour";
				else if (graphType == GraphType.GET_ADJ_MATRIX)
					dialogText = "Configure adjacency matrix";
				else if (graphType == GraphType.FROM_ADJ_MATRIX)
					dialogText = "Input adjacency matrix";
				else if (graphType == GraphType.PETERSEN)
					dialogText = "Configure Petersen graph";
				else if (graphType == GraphType.WHEEL)
					dialogText = "Configure Wheel graph";
				else if (graphType == GraphType.STAR)
					dialogText = "Configure Star graph";
				else if (graphType == GraphType.PATH)
					dialogText = "Configure Path graph";
				else if (graphType == GraphType.FRIENDSHIP_WINDMILL)
					dialogText = "Configure Friendship Windmill graph";
				else if (graphType == GraphType.INDEGREE)
					dialogText = "Configure indegree analysis";
				else if (graphType == GraphType.OUTDEGREE)
					dialogText = "Configure outdegree analysis";
				GraphConfigDialog dialog = new GraphConfigDialog(graphType, dialogText);
				dialog.configureLayout(graph, graphType, aGraph);
				dialog.setModal(true);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension frameSize = dialog.getSize();
				dialog.setLocation(screenSize.width / 2 - (frameSize.width / 2), screenSize.height / 2 - (frameSize.height / 2));
				dialog.setVisible(true);
			}
		}
	}

	/**
	 *
	 */
	public static class AnalyzeGraph extends AbstractAction
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6926170745240507985L;

		mxAnalysisGraph aGraph;

		/**
		 * 
		 */
		protected AnalyzeType analyzeType;

		/**
		 * Examples for calling analysis methods from mxGraphStructure 
		 */
		public AnalyzeGraph(AnalyzeType analyzeType, mxAnalysisGraph aGraph)
		{
			this.analyzeType = analyzeType;
			this.aGraph = aGraph;
		}

		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e.getSource();
				mxGraph graph = graphComponent.getGraph();

				if (analyzeType == AnalyzeType.IS_CONNECTED)
				{
					boolean isConnected = mxGraphStructure.isConnected(aGraph);

					if (isConnected)
					{
						System.out.println("The graph is connected");
					}
					else
					{
						System.out.println("The graph is not connected");
					}
				}
				else if (analyzeType == AnalyzeType.IS_SIMPLE)
				{
					boolean isSimple = mxGraphStructure.isSimple(aGraph);

					if (isSimple)
					{
						System.out.println("The graph is simple");
					}
					else
					{
						System.out.println("The graph is not simple");
					}
				}
				else if (analyzeType == AnalyzeType.IS_CYCLIC_DIRECTED)
				{
					boolean isCyclicDirected = mxGraphStructure.isCyclicDirected(aGraph);

					if (isCyclicDirected)
					{
						System.out.println("The graph is cyclic directed");
					}
					else
					{
						System.out.println("The graph is acyclic directed");
					}
				}
				else if (analyzeType == AnalyzeType.IS_CYCLIC_UNDIRECTED)
				{
					boolean isCyclicUndirected = mxGraphStructure.isCyclicUndirected(aGraph);

					if (isCyclicUndirected)
					{
						System.out.println("The graph is cyclic undirected");
					}
					else
					{
						System.out.println("The graph is acyclic undirected");
					}
				}
				else if (analyzeType == AnalyzeType.COMPLEMENTARY)
				{
					graph.getModel().beginUpdate();

					mxGraphStructure.complementaryGraph(aGraph);

					mxGraphStructure.setDefaultGraphStyle(aGraph, true);
					graph.getModel().endUpdate();
				}
				else if (analyzeType == AnalyzeType.REGULARITY)
				{
					try
					{
						int regularity = mxGraphStructure.regularity(aGraph);
						System.out.println("Graph regularity is: " + regularity);
					}
					catch (StructuralException e1)
					{
						System.out.println("The graph is irregular");
					}
				}
				else if (analyzeType == AnalyzeType.COMPONENTS)
				{
					Object[][] components = mxGraphStructure.getGraphComponents(aGraph);
					mxIGraphModel model = aGraph.getGraph().getModel();

					for (int i = 0; i < components.length; i++)
					{
						System.out.print("Component " + i + " :");

						for (int j = 0; j < components[i].length; j++)
						{
							System.out.print(" " + model.getValue(components[i][j]));
						}

						System.out.println(".");
					}

					System.out.println("Number of components: " + components.length);

				}
				else if (analyzeType == AnalyzeType.MAKE_CONNECTED)
				{
					graph.getModel().beginUpdate();

					if (!mxGraphStructure.isConnected(aGraph))
					{
						mxGraphStructure.makeConnected(aGraph);
						mxGraphStructure.setDefaultGraphStyle(aGraph, false);
					}

					graph.getModel().endUpdate();
				}
				else if (analyzeType == AnalyzeType.MAKE_SIMPLE)
				{
					mxGraphStructure.makeSimple(aGraph);
				}
				else if (analyzeType == AnalyzeType.IS_TREE)
				{
					boolean isTree = mxGraphStructure.isTree(aGraph);

					if (isTree)
					{
						System.out.println("The graph is a tree");
					}
					else
					{
						System.out.println("The graph is not a tree");
					}
				}
				else if (analyzeType == AnalyzeType.ONE_SPANNING_TREE)
				{
					try
					{
						graph.getModel().beginUpdate();
						aGraph.getGenerator().oneSpanningTree(aGraph, true, true);
						mxGraphStructure.setDefaultGraphStyle(aGraph, false);
						graph.getModel().endUpdate();
					}
					catch (StructuralException e1)
					{
						System.out.println("The graph must be simple and connected");
					}
				}
				else if (analyzeType == AnalyzeType.IS_DIRECTED)
				{
					boolean isDirected = mxGraphProperties.isDirected(aGraph.getProperties(), mxGraphProperties.DEFAULT_DIRECTED);

					if (isDirected)
					{
						System.out.println("The graph is directed.");
					}
					else
					{
						System.out.println("The graph is undirected.");
					}
				}
				else if (analyzeType == AnalyzeType.GET_CUT_VERTEXES)
				{
					Object[] cutVertices = mxGraphStructure.getCutVertices(aGraph);

					System.out.print("Cut vertices of the graph are: [");
					mxIGraphModel model = aGraph.getGraph().getModel();

					for (int i = 0; i < cutVertices.length; i++)
					{
						System.out.print(" " + model.getValue(cutVertices[i]));
					}

					System.out.println(" ]");
				}
				else if (analyzeType == AnalyzeType.GET_CUT_EDGES)
				{
					Object[] cutEdges = mxGraphStructure.getCutEdges(aGraph);

					System.out.print("Cut edges of the graph are: [");
					mxIGraphModel model = aGraph.getGraph().getModel();

					for (int i = 0; i < cutEdges.length; i++)
					{
						System.out.print(" " + Integer.parseInt((String) model.getValue(aGraph.getTerminal(cutEdges[i], true))) + "-"
								+ Integer.parseInt((String) model.getValue(aGraph.getTerminal(cutEdges[i], false))));
					}

					System.out.println(" ]");
				}
				else if (analyzeType == AnalyzeType.GET_SOURCES)
				{
					try
					{
						Object[] sourceVertices = mxGraphStructure.getSourceVertices(aGraph);
						System.out.print("Source vertices of the graph are: [");
						mxIGraphModel model = aGraph.getGraph().getModel();

						for (int i = 0; i < sourceVertices.length; i++)
						{
							System.out.print(" " + model.getValue(sourceVertices[i]));
						}

						System.out.println(" ]");
					}
					catch (StructuralException e1)
					{
						System.out.println(e1);
					}
				}
				else if (analyzeType == AnalyzeType.GET_SINKS)
				{
					try
					{
						Object[] sinkVertices = mxGraphStructure.getSinkVertices(aGraph);
						System.out.print("Sink vertices of the graph are: [");
						mxIGraphModel model = aGraph.getGraph().getModel();

						for (int i = 0; i < sinkVertices.length; i++)
						{
							System.out.print(" " + model.getValue(sinkVertices[i]));
						}

						System.out.println(" ]");
					}
					catch (StructuralException e1)
					{
						System.out.println(e1);
					}
				}
				else if (analyzeType == AnalyzeType.PLANARITY)
				{
					//TODO implement
				}
				else if (analyzeType == AnalyzeType.IS_BICONNECTED)
				{
					boolean isBiconnected = mxGraphStructure.isBiconnected(aGraph);

					if (isBiconnected)
					{
						System.out.println("The graph is biconnected.");
					}
					else
					{
						System.out.println("The graph is not biconnected.");
					}
				}
				else if (analyzeType == AnalyzeType.GET_BICONNECTED)
				{
					//TODO implement
				}
				else if (analyzeType == AnalyzeType.SPANNING_TREE)
				{
					//TODO implement
				}
				else if (analyzeType == AnalyzeType.FLOYD_ROY_WARSHALL)
				{
					
					ArrayList<Object[][]> FWIresult = new ArrayList<Object[][]>();
					try
					{
						//only this line is needed to get the result from Floyd-Roy-Warshall, the rest is code for displaying the result
						FWIresult = mxTraversal.floydRoyWarshall(aGraph);

						Object[][] dist = FWIresult.get(0);
						Object[][] paths = FWIresult.get(1);
						Object[] vertices = aGraph.getChildVertices(aGraph.getGraph().getDefaultParent());
						int vertexNum = vertices.length;
						System.out.println("Distances are:");

						for (int i = 0; i < vertexNum; i++)
						{
							System.out.print("[");

							for (int j = 0; j < vertexNum; j++)
							{
								System.out.print(" " + Math.round((Double) dist[i][j] * 100.0) / 100.0);
							}

							System.out.println("] ");
						}

						System.out.println("Path info:");

						mxCostFunction costFunction = aGraph.getGenerator().getCostFunction();
						mxGraphView view = aGraph.getGraph().getView();

						for (int i = 0; i < vertexNum; i++)
						{
							System.out.print("[");

							for (int j = 0; j < vertexNum; j++)
							{
								if (paths[i][j] != null)
								{
									System.out.print(" " + costFunction.getCost(view.getState(paths[i][j])));
								}
								else
								{
									System.out.print(" -");
								}
							}

							System.out.println(" ]");
						}

						try
						{
							Object[] path = mxTraversal.getWFIPath(aGraph, FWIresult, vertices[0], vertices[vertexNum - 1]);
							System.out.print("The path from " + costFunction.getCost(view.getState(vertices[0])) + " to "
									+ costFunction.getCost((view.getState(vertices[vertexNum - 1]))) + " is:");

							for (int i = 0; i < path.length; i++)
							{
								System.out.print(" " + costFunction.getCost(view.getState(path[i])));
							}

							System.out.println();
						}
						catch (StructuralException e1)
						{
							System.out.println(e1);
						}
					}
					catch (StructuralException e2)
					{
						System.out.println(e2);
					}
				}
			}
		}
	};
};