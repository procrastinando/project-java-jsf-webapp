package bean;

import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 * swithc language bean
 */
@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {
    private String selectedLanguage;

    public LanguageBean() {

    }

    public String getSelectedLanguage() {
        if (selectedLanguage == null) {
            selectedLanguage = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(selectedLanguage));
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
        // refresh page apply new theme
        PrimeFaces.current().executeScript("window.location.reload()");
    }
}
