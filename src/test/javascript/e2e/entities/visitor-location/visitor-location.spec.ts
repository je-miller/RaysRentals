// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { VisitorLocationComponentsPage, VisitorLocationDeleteDialog, VisitorLocationUpdatePage } from './visitor-location.page-object';

const expect = chai.expect;

describe('VisitorLocation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let visitorLocationComponentsPage: VisitorLocationComponentsPage;
  let visitorLocationUpdatePage: VisitorLocationUpdatePage;
  let visitorLocationDeleteDialog: VisitorLocationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load VisitorLocations', async () => {
    await navBarPage.goToEntity('visitor-location');
    visitorLocationComponentsPage = new VisitorLocationComponentsPage();
    await browser.wait(ec.visibilityOf(visitorLocationComponentsPage.title), 5000);
    expect(await visitorLocationComponentsPage.getTitle()).to.eq('Visitor Locations');
  });

  it('should load create VisitorLocation page', async () => {
    await visitorLocationComponentsPage.clickOnCreateButton();
    visitorLocationUpdatePage = new VisitorLocationUpdatePage();
    expect(await visitorLocationUpdatePage.getPageTitle()).to.eq('Create or edit a Visitor Location');
    await visitorLocationUpdatePage.cancel();
  });

  it('should create and save VisitorLocations', async () => {
    const nbButtonsBeforeCreate = await visitorLocationComponentsPage.countDeleteButtons();

    await visitorLocationComponentsPage.clickOnCreateButton();
    await promise.all([
      visitorLocationUpdatePage.setIpInput('ip'),
      visitorLocationUpdatePage.setCountryCodeInput('countryCode'),
      visitorLocationUpdatePage.setCountryNameInput('countryName'),
      visitorLocationUpdatePage.setRegionInput('region'),
      visitorLocationUpdatePage.setCityInput('city'),
      visitorLocationUpdatePage.setLatitudeInput('5'),
      visitorLocationUpdatePage.setLongitudeInput('5'),
      visitorLocationUpdatePage.setZipcodeInput('zipcode'),
      visitorLocationUpdatePage.setTimezoneInput('timezone')
    ]);
    expect(await visitorLocationUpdatePage.getIpInput()).to.eq('ip', 'Expected Ip value to be equals to ip');
    expect(await visitorLocationUpdatePage.getCountryCodeInput()).to.eq(
      'countryCode',
      'Expected CountryCode value to be equals to countryCode'
    );
    expect(await visitorLocationUpdatePage.getCountryNameInput()).to.eq(
      'countryName',
      'Expected CountryName value to be equals to countryName'
    );
    expect(await visitorLocationUpdatePage.getRegionInput()).to.eq('region', 'Expected Region value to be equals to region');
    expect(await visitorLocationUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await visitorLocationUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await visitorLocationUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await visitorLocationUpdatePage.getZipcodeInput()).to.eq('zipcode', 'Expected Zipcode value to be equals to zipcode');
    expect(await visitorLocationUpdatePage.getTimezoneInput()).to.eq('timezone', 'Expected Timezone value to be equals to timezone');
    await visitorLocationUpdatePage.save();
    expect(await visitorLocationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await visitorLocationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last VisitorLocation', async () => {
    const nbButtonsBeforeDelete = await visitorLocationComponentsPage.countDeleteButtons();
    await visitorLocationComponentsPage.clickOnLastDeleteButton();

    visitorLocationDeleteDialog = new VisitorLocationDeleteDialog();
    expect(await visitorLocationDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Visitor Location?');
    await visitorLocationDeleteDialog.clickOnConfirmButton();

    expect(await visitorLocationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
