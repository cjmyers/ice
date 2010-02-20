package org.jbei.ice.web.forms;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.jbei.ice.lib.models.Plasmid;
import org.jbei.ice.lib.models.SelectionMarker;
import org.jbei.ice.web.common.CommaSeparatedField;
import org.jbei.ice.web.common.FormException;

public class PlasmidNewFormPanel extends Panel {
    private static final long serialVersionUID = 1L;

    public PlasmidNewFormPanel(String id) {
        super(id);

        PlasmidForm form = new PlasmidForm("plasmidForm");
        form.add(new Button("submitButton"));
        add(form);

        add(new FeedbackPanel("feedback"));
    }

    @SuppressWarnings("unused")
    private class PlasmidForm extends EntrySubmitForm<Plasmid> {
        private static final long serialVersionUID = 1L;

        // plasmid only fields
        private String selectionMarkers;
        private String backbone;
        private String originOfReplication;
        private String promoters;
        private boolean circular = true;

        public PlasmidForm(String id) {
            super(id);

            setEntry(new Plasmid());

            add(new TextField<String>("selectionMarkers", new PropertyModel<String>(this,
                    "selectionMarkers")));
            add(new TextField<String>("backbone", new PropertyModel<String>(this, "backbone")));
            add(new TextField<String>("originOfReplication", new PropertyModel<String>(this,
                    "originOfReplication")));
            add(new TextField<String>("promoters", new PropertyModel<String>(this, "promoters")));
            add(new CheckBox("circular", new PropertyModel<Boolean>(this, "circular")));

            setCircular(true);
        }

        @Override
        protected void populateEntry() {
            super.populateEntry();

            Plasmid plasmid = getEntry();

            try {
                CommaSeparatedField<SelectionMarker> selectionMarkersField = new CommaSeparatedField<SelectionMarker>(
                        SelectionMarker.class, "getName", "setName");
                selectionMarkersField.setString(getSelectionMarkers());
                plasmid.setSelectionMarkers(selectionMarkersField.getItemsAsSet());
            } catch (FormException e) {
                e.printStackTrace();
            }

            plasmid.setBackbone(getBackbone());
            plasmid.setOriginOfReplication(getOriginOfReplication());
            plasmid.setPromoters(getPromoters());
            plasmid.setCircular(getCircular());
        }

        // Getters and setters for PlasmidForm
        public void setSelectionMarkers(String selectionMarkers) {
            this.selectionMarkers = selectionMarkers;
        }

        public String getSelectionMarkers() {
            return selectionMarkers;
        }

        public String getBackbone() {
            return backbone;
        }

        public void setBackbone(String backbone) {
            this.backbone = backbone;
        }

        public String getOriginOfReplication() {
            return originOfReplication;
        }

        public void setOriginOfReplication(String originOfReplication) {
            this.originOfReplication = originOfReplication;
        }

        public String getPromoters() {
            return promoters;
        }

        public void setPromoters(String promoters) {
            this.promoters = promoters;
        }

        public boolean getCircular() {
            return circular;
        }

        public void setCircular(boolean circular) {
            this.circular = circular;
        }
    }
}
