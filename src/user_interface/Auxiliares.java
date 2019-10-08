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
                /* PALABRAS RESERVADAS */
                .replace("public", "<b style=\"color:#9f309f\">public</b>")
                .replace("private", "<b style=\"color:#9f309f\">private</b>")
                .replace("void", "<b style=\"color:#9f309f\">void</b>")
                .replace("Double", "<b style=\"color:#9f309f\">Double</b>")
                .replace("static", "<b style=\"color:#9f309f\">static</b>")
                .replace("boolean", "<b style=\"color:#9f309f\">boolean</b>")
                .replace("this", "<b style=\"color:#9f309f\">this</b>")
                .replace("null", "<b style=\"color:#9f309f\">null</b>")
                .replace("String", "<b style=\"color:#9f309f\">String</b>")
                .replace("Integer", "<b style=\"color:#9f309f\">Integer</b>")
                .replace("class", "<b style=\"color:#9f309f\">class</b>")
                .replace("super", "<b style=\"color:#9f309f\">super</b>")
                /* OPERACIONES */
                .replace("for ", "<b style=\"color:Blue\">for</b>&nbsp;")
                .replace("for(", "<b style=\"color:Blue\">for</b>(")
                .replace("if ", "<b style=\"color:Blue\">if</b>&nbsp;")
                .replace("if(", "<b style=\"color:Blue\">if</b>(")
                .replace("while", "<b style=\"color:Blue\">while</b>&nbsp;")
                .replace("while(", "<b style=\"color:Blue\">while</b>(")
                .replace("case ", "<b style=\"color:Blue\">case</b>&nbsp;")
                .replace("else ", "<b style=\"color:Blue\">else</b>&nbsp;")
                /* COMENTARIOS */
                .replace("//", "<b style=\"color:Red\">//</b>&nbsp;")
                .replace("/*", "<b style=\"color:Red\">/*</b>&nbsp;")
                .replace("*", "<b style=\"color:Red\">*</b>&nbsp;")
                .replace("*/", "<b style=\"color:Red\">*/</b>&nbsp;")
                
                .replace("\n", "<br/>")
                + "</html>";
    }
}
