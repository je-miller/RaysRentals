import { element, by, ElementFinder } from 'protractor';

export class VisitorLocationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-visitor-location div table .btn-danger'));
  title = element.all(by.css('jhi-visitor-location div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class VisitorLocationUpdatePage {
  pageTitle = element(by.id('jhi-visitor-location-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  ipInput = element(by.id('field_ip'));
  countryCodeInput = element(by.id('field_countryCode'));
  countryNameInput = element(by.id('field_countryName'));
  regionInput = element(by.id('field_region'));
  cityInput = element(by.id('field_city'));
  latitudeInput = element(by.id('field_latitude'));
  longitudeInput = element(by.id('field_longitude'));
  zipcodeInput = element(by.id('field_zipcode'));
  timezoneInput = element(by.id('field_timezone'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setIpInput(ip) {
    await this.ipInput.sendKeys(ip);
  }

  async getIpInput() {
    return await this.ipInput.getAttribute('value');
  }

  async setCountryCodeInput(countryCode) {
    await this.countryCodeInput.sendKeys(countryCode);
  }

  async getCountryCodeInput() {
    return await this.countryCodeInput.getAttribute('value');
  }

  async setCountryNameInput(countryName) {
    await this.countryNameInput.sendKeys(countryName);
  }

  async getCountryNameInput() {
    return await this.countryNameInput.getAttribute('value');
  }

  async setRegionInput(region) {
    await this.regionInput.sendKeys(region);
  }

  async getRegionInput() {
    return await this.regionInput.getAttribute('value');
  }

  async setCityInput(city) {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput() {
    return await this.cityInput.getAttribute('value');
  }

  async setLatitudeInput(latitude) {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput() {
    return await this.latitudeInput.getAttribute('value');
  }

  async setLongitudeInput(longitude) {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput() {
    return await this.longitudeInput.getAttribute('value');
  }

  async setZipcodeInput(zipcode) {
    await this.zipcodeInput.sendKeys(zipcode);
  }

  async getZipcodeInput() {
    return await this.zipcodeInput.getAttribute('value');
  }

  async setTimezoneInput(timezone) {
    await this.timezoneInput.sendKeys(timezone);
  }

  async getTimezoneInput() {
    return await this.timezoneInput.getAttribute('value');
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class VisitorLocationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-visitorLocation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-visitorLocation'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
