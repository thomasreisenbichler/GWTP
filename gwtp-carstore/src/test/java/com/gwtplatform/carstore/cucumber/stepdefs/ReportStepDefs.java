/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.carstore.cucumber.stepdefs;

import javax.inject.Inject;

import com.gwtplatform.carstore.cucumber.application.ratings.RatingPage;
import com.gwtplatform.carstore.cucumber.application.reports.ReportPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class ReportStepDefs {
    private static final String CAR_COL = "Car";
    private static final String RATING_COL = "Rating";

    private final RatingPage ratingPage;
    private final ReportPage reportPage;

    @Inject
    ReportStepDefs(RatingPage ratingPage,
                   ReportPage reportPage) {
        this.ratingPage = ratingPage;
        this.reportPage = reportPage;
    }

    @Given("^I compute rating average for all manufacturers$")
    public void I_compute_rating_average_for_all_manufacturers() {
        int numberOfLines = ratingPage.getNumberOfLines();

        for (int line = 1; line <= numberOfLines; line++) {
            String car = ratingPage.getCellText(CAR_COL, line);
            String rating = ratingPage.getCellText(RATING_COL, line);

            reportPage.addRating(car, rating);
        }
    }

    @Then("^I see the proper averages for all manufacturers$")
    public void I_see_proper_averages_for_all_manufacturers() {
        assertTrue(reportPage.checkManufacturerAverages());
    }
}
