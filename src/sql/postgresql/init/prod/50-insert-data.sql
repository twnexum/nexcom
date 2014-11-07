-- Author: Thomas Weckert (thomas.weckert@nexum.de)


-- delete existing table contents
DELETE FROM PRODUCTS;
DELETE FROM ATTRIBUTES;
DELETE FROM PRICES;
DELETE FROM INVENTORIES;


-- product #1
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('4c4d430b-5a26-4eab-b3ec-a0dd582e7b86',FALSE,NULL,NULL);

-- attributes product #1
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('47c27497-cb38-4b3a-9c2c-f012bd92927a','4c4d430b-5a26-4eab-b3ec-a0dd582e7b86','title','Handschuhe aus Biobaumwolle');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('91be248b-415a-464b-bf46-db12631abd55','4c4d430b-5a26-4eab-b3ec-a0dd582e7b86','orderId','GBOC695');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('7f553cff-a690-485d-8693-bd35bdd41dfd','4c4d430b-5a26-4eab-b3ec-a0dd582e7b86','description','Schicke Unisex Strickhandschuhe aus 100% Biobaumwolle, fair und und ohne kritische Chemikalien hergestellt. Der rechte Handschuh ist jeweils mit einem "toxic free" Aufnäher aus recyceltem Polyester versehen.');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('ddd993bd-1b30-4efd-8c17-040d8b19709e','4c4d430b-5a26-4eab-b3ec-a0dd582e7b86','image','/images/products/product-GBOC695/Handschuhe_bio_fair.jpg');

-- price product #1
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('5210ae2f-cafa-44e9-9c9f-26df098bb382', '4c4d430b-5a26-4eab-b3ec-a0dd582e7b86', 39.95, 'EUR');

-- inventory product #1
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('6e718f85-de64-42b0-a88e-082e84bc8211','4c4d430b-5a26-4eab-b3ec-a0dd582e7b86',11);


-- product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('395478fc-8fc1-4262-88db-88e73da28533',TRUE,NULL,'{color,size}');

-- attributes product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('fc5880eb-14da-4576-b89a-3c0fe2bbfd6f','395478fc-8fc1-4262-88db-88e73da28533','title','T-Shirt aus Bio-Baumwolle in div. Farben und Grössen');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('5872a38f-c822-4eec-b069-c4c3bb2556eb','395478fc-8fc1-4262-88db-88e73da28533','description','Hochwertiges Biobaumwoll-T-Shirt für Männer aus extraweichem, \"mitteldickem\" Stoff gefertigt. Durch die leichte vintage-Waschung und den Körperbetonten - aber nicht zu engen - Schnitt erhält das Shirt einen lässigen Look. Das Nackenband ist für Tragekomfort und Formstabilität extra verstärkt. Der Hersteller Stanley & Stella ist Mitglied der Fair Wear Foundation (FWF). Die verwendete Biobaumwolle ist G.O.T.S zertifiziert und erfüllt somit strenge Auflagen in Bezug auf den biologischen Anbau und die schadstoffarme Herstellung des fertigen T-Shirts.');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('3cf76728-6c7a-42b1-9f5c-7944ccb3a3b7','395478fc-8fc1-4262-88db-88e73da28533','image','/images/products/product-GSTTM516/green.jpg');



-- variant #1 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('268e48a2-857d-4618-8f8e-9f68bd81327b',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #1 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('b4f6d887-9f15-4772-b00a-4aefebe90efd','268e48a2-857d-4618-8f8e-9f68bd81327b','title','T-Shirt aus Bio-Baumwolle in L, grün');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('92bc8914-b2ad-4eb1-9d94-4b408af95afa','268e48a2-857d-4618-8f8e-9f68bd81327b','orderId','GSTTM516-221');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('229a85e8-544d-46f6-8881-b5adb2e8cb88','268e48a2-857d-4618-8f8e-9f68bd81327b','color','green');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('5e9681af-9f27-4288-aed1-daf881cc6b6f','268e48a2-857d-4618-8f8e-9f68bd81327b','size','L (x=52cm y=72cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a9ece31b-c974-4dad-b0c3-de710c2e2903','268e48a2-857d-4618-8f8e-9f68bd81327b','image','/images/products/product-GSTTM516/green.jpg');

-- price variant #1 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('e37ddc4d-b4e1-4919-a909-02c134be6df7', '268e48a2-857d-4618-8f8e-9f68bd81327b', 15.95, 'EUR');

-- inventory variant #1 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('a9073261-d88f-4482-a4e7-96e57baf1b9e','268e48a2-857d-4618-8f8e-9f68bd81327b',8);



-- variant #2 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('6eb98b54-dd36-414f-8e63-8a65cd354ec8',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #2 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('e6708352-8ed9-4bb1-b6df-7112cb916016','6eb98b54-dd36-414f-8e63-8a65cd354ec8','title','T-Shirt aus Bio-Baumwolle in L, blau');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('7eea5d7e-bc8f-4941-8106-a0c59f0c26ff','6eb98b54-dd36-414f-8e63-8a65cd354ec8','orderId','GSTTM516-222');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('27f7793e-06b7-45f6-8e13-49f25c4ab2f5','6eb98b54-dd36-414f-8e63-8a65cd354ec8','color','blue');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('165343ca-b2c3-4fb3-9105-553ba3606ac1','6eb98b54-dd36-414f-8e63-8a65cd354ec8','size','L (x=52cm y=72cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('aec94d4d-031e-4227-bee5-73595f71cf7f','6eb98b54-dd36-414f-8e63-8a65cd354ec8','image','/images/products/product-GSTTM516/blue.jpg');

-- price variant #2 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('eb494b25-e68b-4ec1-a6b7-3490afcf64fc', '6eb98b54-dd36-414f-8e63-8a65cd354ec8', 15.95, 'EUR');

-- inventory variant #2 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('573774a3-0d4b-44e2-89b9-4da5272032c7','6eb98b54-dd36-414f-8e63-8a65cd354ec8',19);



-- variant #3 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('05acb29e-f56a-4e74-8862-1b968debe00d',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #3 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('4d833caf-d573-45b6-86b9-5708afe31553','05acb29e-f56a-4e74-8862-1b968debe00d','title','T-Shirt aus Bio-Baumwolle in L, rot');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('bc940a6e-be2f-4d4c-92d2-ab64c6661883','05acb29e-f56a-4e74-8862-1b968debe00d','orderId','GSTTM516-223');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('172ba1c1-ad86-439e-979d-9b002bc2f541','05acb29e-f56a-4e74-8862-1b968debe00d','color','red');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('b10bca9d-fdcc-4607-8bac-4af52ae4d4ad','05acb29e-f56a-4e74-8862-1b968debe00d','size','L (x=52cm y=72cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('909b3b47-1fac-4be7-9bb8-7c9df4557e77','05acb29e-f56a-4e74-8862-1b968debe00d','image','/images/products/product-GSTTM516/red.jpg');

-- price variant #3 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('a008d8ad-9951-4174-92e9-d0a17161130f', '05acb29e-f56a-4e74-8862-1b968debe00d', 15.95, 'EUR');

-- inventory variant #3 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('4b6e0a68-d1ed-45d2-87ea-df0f8a8f35ac','05acb29e-f56a-4e74-8862-1b968debe00d',2);



-- variant #4 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('8936b6ef-a743-493b-b2d7-7aacc9641a45',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #4 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('6ef26a1f-1ce1-4305-a49f-fe96971eec9e','8936b6ef-a743-493b-b2d7-7aacc9641a45','title','T-Shirt aus Bio-Baumwolle in XXL, grün');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('c9d40d2d-9051-41ed-afb2-4f2a4329233b','8936b6ef-a743-493b-b2d7-7aacc9641a45','orderId','GSTTM516-231');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('0995938d-b676-4084-9a22-fdd33c52e005','8936b6ef-a743-493b-b2d7-7aacc9641a45','color','green');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('ed3c43e9-c12a-470f-aaa1-48002cb8c977','8936b6ef-a743-493b-b2d7-7aacc9641a45','size','XXL (x=58cm y=76cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a31b8b8b-1386-4a2f-951a-6d9ceb98ee0f','8936b6ef-a743-493b-b2d7-7aacc9641a45','image','/images/products/product-GSTTM516/green.jpg');

-- price variant #4 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('6abd4d13-c79f-4b8a-b030-360736d71e28', '8936b6ef-a743-493b-b2d7-7aacc9641a45', 18.95, 'EUR');

-- inventory variant #4 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('fc6f2b5f-82fa-4ce7-abca-65907a16fcf8','8936b6ef-a743-493b-b2d7-7aacc9641a45',22);



-- variant #5 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('6e6d154e-915d-4e3a-86bd-bf5ca8fc0123',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #5 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('3d753075-9817-42e4-a130-845e85833c4e','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123','title','T-Shirt aus Bio-Baumwolle in XXL, blau');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('913144f3-cdac-4878-8fe5-96a3b2c66058','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123','orderId','GSTTM516-232');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('106f2848-5e6e-471b-b125-c80451e484f2','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123','color','blue');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('9ad1d948-a301-4f0a-a0a7-f0479e2d20f6','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123','size','XXL (x=58cm y=76cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('9b4e57a6-9c3d-4de1-8860-77b9d90feac3','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123','image','/images/products/product-GSTTM516/blue.jpg');

-- price variant #5 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('6a1861b3-75fa-4f0f-ac59-80a5b88aeebd', '6e6d154e-915d-4e3a-86bd-bf5ca8fc0123', 18.95, 'EUR');

-- inventory variant #5 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('2d51218f-45e1-49c7-a05a-aa469ecc4d2e','6e6d154e-915d-4e3a-86bd-bf5ca8fc0123',6);



-- variant #6 product #2
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('6b676036-d74a-4174-b0e7-52051c2f0a83',FALSE,'395478fc-8fc1-4262-88db-88e73da28533',NULL);

-- attributes variant #6 product #2
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('78dc0a5c-4aa0-4a24-b0d6-04f2c08eebf6','6b676036-d74a-4174-b0e7-52051c2f0a83','title','T-Shirt aus Bio-Baumwolle in XXL, rot');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('661a20e5-8694-4fb9-88e8-6fcd418a41ed','6b676036-d74a-4174-b0e7-52051c2f0a83','orderId','GSTTM516-233');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('4bd3baa4-5b57-4534-a51e-d84a8d9879ce','6b676036-d74a-4174-b0e7-52051c2f0a83','color','red');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('06b62661-3bcd-42a9-9667-854ab2e49b26','6b676036-d74a-4174-b0e7-52051c2f0a83','size','XXL (x=58cm y=76cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('39688f56-f2ea-46df-be9b-04a45be651c9','6b676036-d74a-4174-b0e7-52051c2f0a83','image','/images/products/product-GSTTM516/red.jpg');

-- price variant #6 product #2
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('5e812750-0b7e-4b5d-b8af-3212144a3f73', '6b676036-d74a-4174-b0e7-52051c2f0a83', 18.95, 'EUR');

-- inventory variant #6 product #2
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('6e1384b6-626f-45f6-bd24-ba42f1e19270','6b676036-d74a-4174-b0e7-52051c2f0a83',7);



-- product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('f62051f9-4321-43df-bc3d-304c94b02923',TRUE,NULL,'{color,size}');

-- attributes product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('84b44f0d-525e-47db-b525-c73afba0ee73','f62051f9-4321-43df-bc3d-304c94b02923','title','Kapuzenjacke in div. Farben und Grössen');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('4e76f3d3-b10d-4839-9f40-b99642593165','f62051f9-4321-43df-bc3d-304c94b02923','description','Perfekt geschnittene Männer Kapuzenjacke aus Baumwolle und 23% Polyester. Die Sweatjacke verfügt über eine doppellagige Kapuze, einen Metalreißverschluss und einen Tunnelzug mit breiter Kordel in der Farbe »Rohweiss«. Die Kängurutaschen sind an der Oberseite nicht um-, sondern flach angenäht und erhalten dadurch einen leichten raw-cut-Look. Der geringe Polyesteranteil macht die Kapuzenjacke strapazierfähig. Der Schnitt ist körpernah und nicht zu lang. Stanley & Stella ist Mitglied der Fair Wear Foundation (FWF).');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('de60cf0b-109b-4104-8fac-88bffaca1ab4','f62051f9-4321-43df-bc3d-304c94b02923','image','/images/products/product-GSTSM517/black.jpg');



-- variant #1 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('4de5aa32-e1eb-4985-8bce-9394739cab6d',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #1 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('c5ccc222-7c32-4152-be5c-39fc09a16e2a','4de5aa32-e1eb-4985-8bce-9394739cab6d','title','Kapuzenjacke in L, schwarz');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('e27cd9ca-6e47-46e5-9ec2-9d0f9f004e91','4de5aa32-e1eb-4985-8bce-9394739cab6d','orderId','GSTSM517-321');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('4b9e7110-d0b8-473a-9134-daab43aa2076','4de5aa32-e1eb-4985-8bce-9394739cab6d','color','black');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a07352db-0697-468f-9f48-9edb74165672','4de5aa32-e1eb-4985-8bce-9394739cab6d','size','L (x=56cm y=68cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('6fdbee0d-8ef0-4840-9f16-35e552e1b78b','4de5aa32-e1eb-4985-8bce-9394739cab6d','image','/images/products/product-GSTSM517/black.jpg');

-- price variant #1 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('8c1a4de0-6aad-429c-9721-2abe7da842af', '4de5aa32-e1eb-4985-8bce-9394739cab6d', 38.95, 'EUR');

-- inventory variant #1 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('feb980eb-b141-4e91-9c30-e956c3a472bf','4de5aa32-e1eb-4985-8bce-9394739cab6d',9);



-- variant #2 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('b2460c5b-ebd8-4ec2-8ed0-532bbb89c633',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #2 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('949cae64-fa2c-48be-bfa6-cf20256607fe','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633','title','Kapuzenjacke in L, blau');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('84eaab15-be81-4064-8525-ae05881db6a4','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633','orderId','GSTSM517-322');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('19ee8dde-4865-4ed1-88bd-adb0d5c0d46e','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633','color','blue');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('86697a7c-bb8e-480e-9c77-40c0065f1e8d','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633','size','L (x=56cm y=68cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('170a8073-49db-40f4-841a-d629908ee49c','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633','image','/images/products/product-GSTSM517/blue.jpg');

-- price variant #2 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('a370d1d4-d34d-4c0c-a44e-a1b3f502927f', 'b2460c5b-ebd8-4ec2-8ed0-532bbb89c633', 38.95, 'EUR');

-- inventory variant #2 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('2eb7440d-bace-4428-bf34-6fd4317dc7e7','b2460c5b-ebd8-4ec2-8ed0-532bbb89c633',26);



-- variant #3 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('b1770a6b-de21-4754-b877-01752da34eb8',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #3 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('9fabe04b-8328-49dc-905f-e743b2636040','b1770a6b-de21-4754-b877-01752da34eb8','title','Kapuzenjacke in L, rot');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('89a436e8-effb-48a3-9c34-055427babf18','b1770a6b-de21-4754-b877-01752da34eb8','orderId','GSTSM517-323');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a4c5f743-a2dc-4400-b777-44bf4edd42e1','b1770a6b-de21-4754-b877-01752da34eb8','color','red');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('0278164d-2f80-464e-b8b6-6297e8cc5395','b1770a6b-de21-4754-b877-01752da34eb8','size','L (x=56cm y=68cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('dbdd83df-f6fa-4a10-838d-416edfa6934d','b1770a6b-de21-4754-b877-01752da34eb8','image','/images/products/product-GSTSM517/red.jpg');

-- price variant #3 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('ac1c7998-d55a-4caf-8c4d-ba96458e2bf5', 'b1770a6b-de21-4754-b877-01752da34eb8', 38.95, 'EUR');

-- inventory variant #3 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('28b727f4-9e7e-40bb-8c84-28bcf15f2684','b1770a6b-de21-4754-b877-01752da34eb8',3);



-- variant #4 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('f7ce455c-840d-4c88-acde-ae3cc89c1fe5',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #4 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('359b76a5-e85a-488b-af18-e6d1ddc4c457','f7ce455c-840d-4c88-acde-ae3cc89c1fe5','title','Kapuzenjacke in XXL, schwarz');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('41584bea-8a67-41a6-b237-eba7524c899f','f7ce455c-840d-4c88-acde-ae3cc89c1fe5','orderId','GSTSM517-341');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('7d8a933a-b70c-45f7-927e-49d8e85e61b0','f7ce455c-840d-4c88-acde-ae3cc89c1fe5','color','black');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('44521093-476b-49fd-b05d-459b4ca0c17b','f7ce455c-840d-4c88-acde-ae3cc89c1fe5','size','XXL (x=62cm y=74cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('3f981014-a308-4e27-b297-f1e62cf58a3d','f7ce455c-840d-4c88-acde-ae3cc89c1fe5','image','/images/products/product-GSTSM517/black.jpg');

-- price variant #4 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('ace75bd6-5649-4fe6-9c87-e0c17f44f07c', 'f7ce455c-840d-4c88-acde-ae3cc89c1fe5', 45.95, 'EUR');

-- inventory variant #4 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('2d554d21-85f1-4985-a964-ed90d1721e65','f7ce455c-840d-4c88-acde-ae3cc89c1fe5',1);



-- variant #5 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('36902499-526c-4fd2-a6d6-44125aa5ed21',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #5 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('30c79b36-ef0b-4692-ad57-bdc7b489dfa6','36902499-526c-4fd2-a6d6-44125aa5ed21','title','Kapuzenjacke in XXL, blau');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('778dc188-5d5b-4b1c-ac37-b72407d72ee5','36902499-526c-4fd2-a6d6-44125aa5ed21','orderId','GSTSM517-342');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a2c7306a-74a6-433c-b10a-91f7d8c156e4','36902499-526c-4fd2-a6d6-44125aa5ed21','color','blue');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('45b8cf01-36dd-437c-bfe9-a9a3dabb5763','36902499-526c-4fd2-a6d6-44125aa5ed21','size','XXL (x=62cm y=74cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('8f858d14-13eb-4b4b-a19f-efd3c3bce662','36902499-526c-4fd2-a6d6-44125aa5ed21','image','/images/products/product-GSTSM517/blue.jpg');

-- price variant #5 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('7fb9c229-d34a-4252-b0dc-f73eff68cd01', '36902499-526c-4fd2-a6d6-44125aa5ed21', 45.95, 'EUR');

-- inventory variant #5 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('c760caf1-ffaa-4b5d-89e0-402d005927cb','36902499-526c-4fd2-a6d6-44125aa5ed21',11);



-- variant #6 product #3
INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES ('11bb9f10-5ce6-41b9-88b8-ff38af6b87d9',FALSE,'f62051f9-4321-43df-bc3d-304c94b02923',NULL);

-- attributes variant #6 product #3
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('efd29b47-e945-465b-85af-ab5f01c5f02b','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9','title','Kapuzenjacke in XXL, rot');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('3e0ad942-ccba-4a91-b6c3-ae5e73fca92f','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9','orderId','GSTSM517-343');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('a747d7c8-f3c7-44bb-a6db-d0bccff1825a','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9','color','red');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('de40ce12-d706-43f2-b301-d55d80ee2256','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9','size','XXL (x=62cm y=74cm)');
INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES ('7831f002-b2bb-46df-8b36-b93e375949e3','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9','image','/images/products/product-GSTSM517/red.jpg');

-- price variant #6 product #3
INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES ('012a3a84-be8e-4c3f-bbde-d8a47360a175', '11bb9f10-5ce6-41b9-88b8-ff38af6b87d9', 45.95, 'EUR');

-- inventory variant #6 product #3
INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES ('c12cc6da-9ff2-4f1f-ac11-16b3d38d15e0','11bb9f10-5ce6-41b9-88b8-ff38af6b87d9',6);