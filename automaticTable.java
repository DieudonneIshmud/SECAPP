 final void getData() {
        MongoCollection coll = MongoUtil.getCollection("testq");
        documents = coll.find();
        String[] columnNames = {"Test Description", "Course Code", "Date", "Time", "Open/Closed"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        int counter = 0;
        for (Document obj : documents) {
            String testTitle = (String) obj.get("Title");
            String courseCode = (String) obj.get("course_code");
            String dateS = obj.getString("Date");
            String timeS = obj.getString("Time");
            Date date = createDateFromDateTime(dateS, timeS);
            String openORclosed = (String) obj.get("Closed/Open");
            Object duratation = obj.get("Duration");
            ObjectId id = (ObjectId) obj.get("_id");
            model.addRow(new Object[]{testTitle, courseCode, date, duratation, openORclosed});
//            table.getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 20));
//            table.getTableHeader().setBackground(new Color(122,71,221));
        }
        table.setModel(model);
        table.setTableHeader(null);
        //table.setIntercellSpacing(new Dimension (10,20));
    }