package com.mygdx.game.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Fruit;
import com.mygdx.game.screens.PlayScreen;

public class NPCDialog {
	//Lớp NPC, lưu trữ thông tin đối thoại

	private Skin skin;
	private Dialog dialogOfVisitor1;
	private Dialog dialogOfVisitor2;

	private Dialog dialogOfTeacher1;
	private Dialog dialogOfTeacher2;
	private Dialog dialogOfTeacher3;

	private Dialog dialogOfSecurityGuard1;
	private Dialog dialogOfSecurityGuard2;

	private Dialog myDialog1;
	private Dialog myDialog2;
	private Dialog myDialog3;
	private Dialog myDialog4;
	private Dialog myDialog5;
	private Dialog dialogOfJudge;

	private Array<Fruit> fruitArray;

	public void setNPCDialog(String NPCName, int dialogProgress, PlayScreen screen) {
		skin = screen.getSkin();
		fruitArray=new Array<Fruit>();
		switch (NPCName) {
		case "visitor": {
			if (dialogProgress == 0) {
				dialogOfVisitor1 = new Dialog("", skin, "npc1") {
					protected void result(Object object) {
						if (object.equals(true)) {
							dialogOfVisitor1.setVisible(false);
							setMyDialog("visitor", 0, screen);
							myDialog1.show(screen.getStage());
						}
					}
				};
				dialogOfVisitor1.text("Bạn ơi, cho mình hỏi đường đến ao sen như thế nào?");
				dialogOfVisitor1.button("Giúp đỡ", true).button("Từ chối",false);
			}

			if (dialogProgress == 1) {
				dialogOfVisitor2 = new Dialog("", skin, "npc1") {
					protected void result(Object object) {
						if (object.equals(true)) {
							Crop crop=screen.getCropArray().random();
							int lucknumber= MathUtils.random(1,5);
							crop.setCropNumber(crop.getCropNumber()+lucknumber);
							screen.getBag().update();
							new Dialog("", skin, "dialog") {
								protected void result(Object object) {
									if (object.equals(true)) {
									}
								}
							}.text("Nhận được" + crop.getName() +"hạt giống"+ lucknumber+"phần").button("Xác nhận", true).show(screen.getStage());
							}
						dialogOfVisitor2.setVisible(false);
						}

				};
				dialogOfVisitor2.text("Cảm ơn bạn, không chỉ cảnh đẹp, sinh viên ở đây cũng rất thân thiện. Mình có một ít hạt giống muốn tặng bạn.");
				dialogOfVisitor2.button("Nhận quà", true);
			}
			break;
		}

		case "teacher": {
			if (dialogProgress == 0) {
				dialogOfTeacher1 = new Dialog("", skin, "npc2") {
					protected void result(Object object) {
						if (object.equals(true)) {
							dialogOfTeacher1.setVisible(false);
							setMyDialog("teacher", 0, screen);
							myDialog2.show(screen.getStage());
						}
					}
				};
				dialogOfTeacher1.text("Lớp trưởng, dạo này các bạn trong lớp phản hồi thế nào về các buổi học?");
				dialogOfTeacher1.button("Tiếp tục đối thoại", true);
				dialogOfTeacher1.setPosition(0, 0);
			}

			if (dialogProgress == 1) {
				dialogOfTeacher2 = new Dialog("", skin, "npc2") {
					protected void result(Object object) {
						if (object.equals(true)) {
							dialogOfTeacher2.setVisible(false);
						}
					}
				};
				dialogOfTeacher2.text("Quả này trông ngon đấy, cảm ơn nhé!");
				dialogOfTeacher2.button("Kết thúc đối thoại", true);
			}
			break;
		}

		case "security": {
			if (dialogProgress == 0) {
				dialogOfSecurityGuard1 = new Dialog("", skin, "npc3") {
					protected void result(Object object) {
						if (object.equals(true)) {
							dialogOfSecurityGuard1.setVisible(false);
							setMyDialog("security", 0, screen);
							myDialog5.show(screen.getStage());
						}
					}
				};
				dialogOfSecurityGuard1.text("Bạn ơi, vui lòng xuất trình thẻ sinh viên！");
				dialogOfSecurityGuard1.button("Tiếp tục đối thoại", true);
			}

			if (dialogProgress == 1) {
				dialogOfSecurityGuard2 = new Dialog("", skin, "npc3") {
					protected void result(Object object) {
						if (object.equals(true)) {
							dialogOfSecurityGuard2.setVisible(false);
						}
					}
				};
				dialogOfSecurityGuard2.text("Được rồi, bây giờ bạn có thể tự do ra vào！");
				dialogOfSecurityGuard2.button("Kết thúc đối thoại", true);
			}
			break;
		}
		}
	}

	public void setMyDialog(String NPCName, int dialogProgress, PlayScreen screen) {
		switch (NPCName) {
			case "visitor": {
				if (dialogProgress == 0) {
					myDialog1 = new Dialog("", skin, "npcme") {
						protected void result(Object object) {
							if (object.equals(true)) {
								myDialog1.setVisible(false);
								setNPCDialog("visitor", 1, screen);
								dialogOfVisitor2.show(screen.getStage());
							}
						}
					};
					myDialog1.text("Bác ơi, bác muốn hỏi về ao sen phải không? Nó ở ngay trước mặt bác đấy！");
					myDialog1.button("Tiếp tục đối thoại", true);

				}
				break;
			}

			case "teacher": {
				if (dialogProgress == 0) {
					myDialog2 = new Dialog("", skin, "npcme") {
						protected void result(Object object) {
							if (object.equals(true)) {
								myDialog2.setVisible(false);
								setNPCDialog("teacher", 1, screen);

								fruitArray.clear();
								for (int i = 0; i < screen.getFruitArray().size; i++) {
									if (screen.getFruitArray().get(i).getFruitNumber() > 0)
										fruitArray.add(screen.getFruitArray().get(i));
								}
								if (fruitArray.size == 0) {
									new Dialog("", skin, "dialog") {
                                        protected void result(Object object) {
                                            if (object.equals(true)) {
                                                dialogOfTeacher2.setVisible(false);
                                            }
                                        }
                                    }.text("Trong túi của bạn không có quả nào").button("Xác nhận", "true").show(screen.getStage());
								} else {
									int num = (int) (Math.random() * fruitArray.size );
									fruitArray.get(num).setFruitNumber(fruitArray.get(num).getFruitNumber() - 1);
									screen.getBag().update();
									new Dialog("", skin, "dialog") {
										protected void result(Object object) {
											if (object.equals(true)) {
                                                dialogOfTeacher2.show(screen.getStage());
											}
										}
									}.text("Đã tặng thành công" + fruitArray.get(num).getName() + "X1").button("Xác nhận", true).show(screen.getStage());
								}
							}
						}
					};
					myDialog2.text("Các bạn trong lớp thấy tốc độ giảng bài khá ổn\nThầy/cô, gần đây trang trại của chúng em có thu hoạch được vài quả ngon, em muốn tặng thầy/cô một ít.");
					myDialog2.button("Tặng quà", true);
				}
				break;
			}

			case "security": {
				if (dialogProgress == 0) {
					myDialog5 = new Dialog("", skin, "npcme") {
						protected void result(Object object) {
							if (object.equals(true)) {
								myDialog5.setVisible(false);
								setNPCDialog("security", 1, screen);
								dialogOfSecurityGuard2.show(screen.getStage());

							}
						}
					};
					myDialog5.text("Chú bảo vệ, đây là thẻ sinh viên của cháu.");
					myDialog5.button("Tiếp tục đối thoại", true);
				}
				break;
			}
		}
	}
	public Dialog getDialogOfVisitor() {
		return dialogOfVisitor1;
	}

	public Dialog getDialogOfTeacher() {
		return dialogOfTeacher1;
	}

	public Dialog getDialogOfSecurityGuard() {
		return dialogOfSecurityGuard1;
	}
	public void setDialog(PlayScreen screen) {
		dialogOfJudge = new Dialog("", skin, "dialog") {
			protected void result(Object object) {
				if (object.equals(true)) {
					dialogOfJudge.setVisible(false);
				}
			}
		};
		dialogOfJudge.text("Hôm nay bạn đã nói chuyện với NPC rồi, quay lại sau 7 giờ sáng mai nhé.");
		dialogOfJudge.button("Tôi biết rồi", true);
		dialogOfJudge.show(screen.getStage());

	}


}
