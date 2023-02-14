package com.ecclesiav2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OrganisationData{
    private ArrayList<Organisation> allOrganisations = new ArrayList<>();
    private ArrayList<Organisation> registeredOrganisations = new ArrayList<>();

    public OrganisationData() {
        String default_org_descriptions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer facilisis eros turpis, ut eleifend arcu pharetra quis. Integer dapibus pulvinar finibus. Nullam euismod tellus ac lectus laoreet, sed scelerisque mauris egestas. Vestibulum nec ex et lorem pulvinar fringilla. Nam eleifend, ligula non accumsan elementum, dui est elementum lacus, nec hendrerit odio nisi vel eros. Curabitur eget ipsum dignissim, faucibus libero at, congue massa. Nam pulvinar, tellus quis finibus venenatis, eros eros viverra dolor, ac consectetur erat lorem nec ante. Donec dapibus felis magna, eget consequat massa dignissim eget. Aenean quis nunc consectetur, rutrum mi in, maximus enim. Vivamus et magna non mi ultricies sollicitudin sit amet ut lorem.";

        allOrganisations.add(new Organisation("0", "org0", default_org_descriptions));
        allOrganisations.add(new Organisation("1", "org1", default_org_descriptions));
        allOrganisations.add(new Organisation("2", "org2", default_org_descriptions));
        allOrganisations.add(new Organisation("3", "org3", default_org_descriptions));

        registeredOrganisations.add(allOrganisations.get(0));
        registeredOrganisations.add(allOrganisations.get(2));
    }

    public ArrayList<Organisation> getAllOrganisations() {
        return allOrganisations;
    }

    public void setAllOrganisations(ArrayList<Organisation> allOrganisations) {
        this.allOrganisations = allOrganisations;
    }

    public ArrayList<Organisation> getRegisteredOrganisations() {
        return registeredOrganisations;
    }

    public void setARegisteredOrganisations(Organisation organisation) {
        registeredOrganisations.add(organisation);
    }
}
