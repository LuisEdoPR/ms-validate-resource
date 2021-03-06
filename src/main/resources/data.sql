drop table if exists resource CASCADE;

create table resource (
   id varchar(255) not null,
    left_data CLOB,
    right_data CLOB,
    primary key (id)
);

insert into resource (id, left_data, right_data) values ('1', 'ewogICJpZCI6IDEsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIldvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==', 'ewogICJpZCI6IDEsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIldvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==');
insert into resource (id, left_data, right_data) values ('2', 'ewogICJpZCI6IDIsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==', null);
insert into resource (id, left_data, right_data) values ('3', null, 'ewogICJpZCI6IDMsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==');
insert into resource (id, left_data, right_data) values ('4', null, null);
insert into resource (id, left_data, right_data) values ('5', 'ewogICJpZCI6IDUsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIsCiAgImV4dHJhIjogIkV4dHJhIERhdGEiCn0=', 'ewogICJpZCI6IDUsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==');
insert into resource (id, left_data, right_data) values ('6', 'ewogICJpZCI6IDYsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8iLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyIKfQ==', 'ewogICJpZCI6IDYsCiAgImRlc2NyaXB0aW9uIjogIldBRVMgZGVtbyIsCiAgImF1dGhvciI6ICJMdWlzIEVkdWFyZG8gUFIiLAogICJkYXRlIjogIjIwMjAtMDMtMDMiLAogICJnb2FsIjogIndvcmsgYXQgV0FFUyBhbmQgbGl2ZSBpbiBOZXRoZXJsYW5kcyAhISEiCn0=');
insert into resource (id, left_data, right_data) values ('7', 'ewogICJ1bm8iOiAxMCwKICAiZG9zIjogImRvcyIsCiAgInRyZXMiOiAidHJlcyIsCiAgImN1YXRybyI6ICI0IiwKICAiY2luY28iOiAiNSIsCiAgImFycmF5IjogWzEsMiwzLDQsNV0sCiAgIm9iamVjdCI6IHsKICAgICAgIm8xIjogMSwKICAgICAgIm8yIjogIjIiLAogICAgICAibzMiOiAiTHVpcyIKICB9Cn0=', null);
insert into resource (id, left_data, right_data) values ('8', 'XXXewogICJ1bm8iOiAxMCwKICAiZG9zIjogImRvcyIsCiAgInRyZXMiOiAidHJlcyIsCiAgImN1YXRybyI6ICI0IiwKICAiY2luY28iOiAiNSIsCiAgImFycmF5IjogWzEsMiwzLDQsOV0sCiAgIm9iamVjdCI6IHsKICAgICAgIm8xIjogMSwKICAgICAgIm8yIjogIjIiLAogICAgICAibzQiOiAiTHVpcyIsCiAgICAgICJvNSI6ICJleHRyYSIsCiAgICAgICJvYmplY3QyIjogewogICAgICAgICAgIm8yLWEiOiAiMiIsCiAgICAgICAgICAibzItYiI6IDEyMzQ1CiAgICAgIH0KICB9Cn0=', 'ewogICJ1bm8iOiAxMCwKICAiZG9zIjogImRvcyIsCiAgInRyZXMiOiAidHJlcyIsCiAgImN1YXRybyI6ICI0IiwKICAiY2luY28iOiAiNSIsCiAgImFycmF5IjogWzEsMiwzLDQsNV0sCiAgIm9iamVjdCI6IHsKICAgICAgIm8xIjogMSwKICAgICAgIm8yIjogIjIiLAogICAgICAibzMiOiAiTHVpcyIKICB9Cn0=');