package mn.ulas.converter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

@SuppressWarnings("rawtypes")
public class ValueLabelComboBoxModel implements ComboBoxModel {

  private final List<CodedValue> values;
  private Object selectedValue;

  public ValueLabelComboBoxModel() {
    values = new ArrayList<>();
  }

  public ValueLabelComboBoxModel(List<CodedValue> values) {
    this();
    this.values.addAll(values);
  }

  @Override
  public void setSelectedItem(Object anItem) {
    this.selectedValue = anItem;
  }

  /**
   * set selected value.
   */
  public void setSelectedItemByValue(String value) {
    for (ValueLabelPair valueLabelPair : values) {
      if (Objects.equals(valueLabelPair.getValue(), value)) {
        this.setSelectedItem(valueLabelPair);
        break;
      }
    }
  }

  /**
   * get item by value.
   */
  public Object getItemByValue(String value) {
    Object result = null;
    for (ValueLabelPair valueLabelPair : values) {
      if (valueLabelPair.getValue().equals(value)) {
        result = valueLabelPair;
        break;
      }
    }
    return result;
  }

  @Override
  public Object getSelectedItem() {
    return selectedValue;
  }

  @Override
  public int getSize() {
    return values.size();
  }

  @Override
  public Object getElementAt(int index) {
    return values.get(index);
  }

  @Override
  public void addListDataListener(ListDataListener l) {
    //
  }

  @Override
  public void removeListDataListener(ListDataListener l) {
    //
  }
}
