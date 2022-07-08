/*import the interface for banks */
import { Bank } from './bank';
export const BANKS: Bank[] = [
{ id: 11, name: 'Bank of America Merrill Lynch', website: new URL("https://business.bofa.com/content/boaml/en_us/home.html")},
{ id: 12, name: 'Barclays Capital', website: new URL("https://www.cib.barclays")},
{ id: 13, name: 'Citi', website: new URL("https://www.citi.com/")},
{ id: 14, name: 'Credit Suisse', website: new URL("https://www.credit-suisse.com/us/en.html")},
{ id: 15, name: 'Deutsche Bank', website:new URL("https://www.db.com/index?language_id=1&kid=sl.redirect-en.shortcut") },
{ id: 16, name: 'Goldman Sachs', website: new URL("https://www.goldmansachs.com/")},
{ id: 17, name: 'J.P. Morgan', website: new URL("https://www.jpmorgan.com/CA/en/about-us")},
{ id: 18, name: 'Morgan Stanley', website: new URL("https://www.morganstanley.com/")},
{ id: 19, name: 'UBS', website: new URL("https://www.ubs.com/ca/en.html")}
];