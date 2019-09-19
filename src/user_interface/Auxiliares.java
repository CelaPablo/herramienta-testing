package user_interface;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Auxiliares {

	public static <T> void cargarLista( List<T> nombres, JList lista ){
        DefaultListModel model = new DefaultListModel();
        for( T s : nombres ){
            model.addElement(s);
        }
        lista.setModel(model);
        lista.setSelectedIndex(0);
    }

    static String getFormatedCode(String codigo) {
        return "<html>" + codigo
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace(" ", "&nbsp;")
                .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
                .replace("for ", "<b style=\"color:Blue\">for</b>&nbsp;")
                .replace("for(", "<b style=\"color:Blue\">for</b>(")
                .replace("if ", "<b style=\"color:Blue\">if</b>&nbsp;")
                .replace("if(", "<b style=\"color:Blue\">if</b>(")
                .replace("while", "<b style=\"color:Blue\">while</b>&nbsp;")
                .replace("while(", "<b style=\"color:Blue\">while</b>(")
                .replace("case ", "<b style=\"color:Blue\">case</b>&nbsp;") 
                .replace("\n", "<br/>")
                + "</html>";
    }
}
