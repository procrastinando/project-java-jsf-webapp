package bean;

import model.Car;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import service.CarService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DataGrid page operation demo
 */
@ManagedBean
@ViewScoped
public class DataGridView implements Serializable {
    /**
     * selected car
     */
    private Car selectedCar;

    /**
     * Paginated data acquisition agent
     */
    private LazyDataModel<Car> items;

    /**
     * Inject carService
     */
    @ManagedProperty(value = "#{carService}")
    private CarService service;

    public DataGridView() {

        // Initialize the paging data acquisition agent
        items = new LazyDataModel<Car>() {
            @Override
            public int count(Map<String, FilterMeta> filters) {
                //Simulate the total number of items obtained
                return service.countCar(filters);
            }

            @Override
            public List<Car> load(int first, int rows, Map<String, SortMeta> sorts, Map<String, FilterMeta> filters) {
                // When the page operation jump page, the following logic will be called.
                System.out.println("获取开始行数：" + first);
                System.out.println("获取条数：" + rows);
                System.out.println("排序信息：" + sorts);
                System.out.println("排序信息：" + filters);

                //Simulate obtaining a specified number of rows of data from the server
                return service.findPage(first, rows);
            }

        };
    }

    /**
     * get car page information
     *
     * @return
     */
    public LazyDataModel<Car> getItems() {
        return items;
    }


    public Car getSelectedCar() {
        FacesContext context = FacesContext.getCurrentInstance();
        // get param by name “carId”
        String carId = context.getExternalContext().getRequestParameterMap().get("carId");
        if (carId != null) {
            // get car by id
            this.selectedCar = service.findCarById(carId);
        }
        
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }


    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, (clientId) -> {
            showMessage(clientId);
        });
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }


    public void setService(CarService service) {
        this.service = service;
    }


    /**
     * The preRenderView event is an event in the JavaServer Faces (JSF) framework that is
     * triggered before the page is rendered. In this event, you can perform operations such
     * as preparing data, handling request parameters, or executing other logic related to
     * the page before rendering.
     */
    public void preRenderView() {

    }
}
