/**/

import groovy.swing.SwingBuilder
import groovy.beans.Bindable
import javax.swing.*
import java.awt.Component

class DB{
	String name
	String toString(){
		return name
	}
}

List<DB> dbs1 = [
	new DB(name:"db1"),
	new DB(name:"db3"),
	new DB(name:"db2"),
]

def getInstance={
	return dbs1;
}

@groovy.transform.CompileStatic
class GListModel<K> extends AbstractListModel<K>{
	/*@Bindable*/ List<K> list
	@Override int getSize(){ list.size() }
	@Override K getElementAt(int i){ list.get(i) }
	//method to update list container
	void fireChange(){ this.fireContentsChanged(this, 0, list.size()) }
}

//shows standard edit dialog to modify one string
//see: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
String prompt(Component parent, String title, String value, String label=null){
	String s = (String)JOptionPane.showInputDialog(
                    parent,
                    (label?:title?:"Input")+":",
                    title?:"Prompt",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, //icon,
                    null, //of possible values could be here
                    value);
    return s;
}

//handle exceptions
@groovy.transform.CompileStatic
class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		try{
			org.codehaus.groovy.runtime.StackTraceUtils.sanitizeRootCause(e).printStackTrace(System.err)
		}catch(Throwable ei){
			System.err.println(">>>"+ei)
		}
	}

	public static void register() {
		Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
		System.setProperty("sun.awt.exception.handler", MyExceptionHandler.class.getName());
	}
}

def onDatabaseDelete={list->
	DB db = list.getSelectedValue();
	getInstance().remove(db);
	list.model.fireChange();
}

def onDatabaseAdd={list->
	String name = prompt( list, "New database name", "NewDB" )
	if(name){
		getInstance().add(new DB(name:name));
		list.model.fireChange();
	}
}


//def model = new DBSModel()

new SwingBuilder().edt {
	frame(title: "Java Frame", size: [800, 600], locationRelativeTo: null, show: true, defaultCloseOperation:JFrame.EXIT_ON_CLOSE) {
		gridLayout(cols: 1, rows: 1)
		scrollPane( verticalScrollBarPolicy:JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ) {
			list(id:"dbList", model: new GListModel(list:getInstance()), selectionMode: ListSelectionModel.SINGLE_SELECTION )
		}
		menuBar{
			menu(text: "File", mnemonic: 'F') {
				menuItem(text: "Exit", mnemonic: 'X', actionPerformed: { dispose() })
			}
			menu(text: "Database", mnemonic: 'D') {
				menuItem(text: "Add",    mnemonic: 'A', actionPerformed: { onDatabaseAdd(dbList) })
				menuItem(text: "Delete", mnemonic: 'D', actionPerformed: { onDatabaseDelete(dbList) })
			}
		}
	}
	MyExceptionHandler.register()
}
