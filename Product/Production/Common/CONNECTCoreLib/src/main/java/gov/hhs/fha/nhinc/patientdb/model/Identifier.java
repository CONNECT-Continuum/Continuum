/*
 * Copyright (c) 2009-2019, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the United States Government nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gov.hhs.fha.nhinc.patientdb.model;

import gov.hhs.fha.nhinc.common.loadtestdatamanagement.IdentifierType;
import gov.hhs.fha.nhinc.util.CoreHelpUtils;
import java.io.Serializable;

/**
 *
 *
 *
 * @author richard.ettema
 */
public class Identifier implements Serializable {

    private static final long serialVersionUID = 5599130866061135046L;

    Long identifierId;
    private Patient patient;
    private String id;
    private String organizationId;

    public Identifier() {
    }
    public Identifier(IdentifierType identifier, Patient patient) {
        this.patient = patient;
        identifierId = CoreHelpUtils.isId(identifier.getIdentifierId()) ? identifier.getIdentifierId() : null;
        id = identifier.getId();
        organizationId = identifier.getOrganizationId();
    }

    public IdentifierType getIdentifierType() {
        IdentifierType build = new IdentifierType();
        build.setPatientId(patient.getPatientId());
        build.setIdentifierId(identifierId);
        build.setId(id);
        build.setOrganizationId(organizationId);
        return build;
    }

    public Long getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(Long identifierId) {
        this.identifierId = identifierId;
    }

    public Patient getPatient() {
        if (patient == null) {
            patient = new Patient();
        }
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    // Helper getter methods
    public String getHl7UniquePatientId() {
        String hl7UniquePatientId = "";
        if (id != null && organizationId != null) {
            hl7UniquePatientId = id + "^^^&" + organizationId + "&ISO";
        }
        return hl7UniquePatientId;
    }

}
